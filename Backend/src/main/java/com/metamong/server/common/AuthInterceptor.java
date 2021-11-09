package com.metamong.server.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.metamong.server.service.JwtService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Api("Interceptor")
public class AuthInterceptor implements HandlerInterceptor {                     // 인터셉터

    @Value("${token.accesstoken}")
    private String accessToken;

    @Value("${token.refreshtoken}")
    private String refreshToken;

    @Value(("${token.secretkey}"))
    private String secretkey;

    @Autowired
    private JwtService jwtService;
    /**
     * 인터셉터 PreHandler : 유저 인증 정보 체크 역할
     * @param request : Client 요청 정보
     * @param response : Server -> Client 응답 정보
     * @param Handler : 핸들러
     * @return : Boolean (True : to Controller, False : Reject Request)
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception{
        System.out.println("(" + request.getMethod() + ")PreHandler / Request Url : " + request.getRequestURI());

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        // JWT 사용자 인증 로직
        String acToken = request.getHeader(accessToken);
        String rfToken = request.getHeader(refreshToken);

        String result = jwtService.decodeToken(acToken, secretkey);
        if(rfToken != null && (result.equals("expire") || result.equals("invalid"))){      // Refresh Token 이 넘어왔을 때
            String retRefresh = jwtService.decodeToken(rfToken, secretkey);
            if(!retRefresh.equals("expire") && !retRefresh.equals("invalid")){          // Access Token 재발행 & API 수행
                Map<String, Object> map = jwtService.createToken(Integer.parseInt(retRefresh));
                response.setHeader(refreshToken, rfToken);
                response.setHeader(accessToken, (String) map.get(accessToken));
                result = retRefresh;
            }
            else{                                                       // Refresh Token 만료
                response.setStatus(409);
                jsonObject.addProperty("msg", "refreshTokenExpired");
                response.getWriter().write(gson.toJson(jsonObject));
                return false;
            }
        }
        else if (result.equals("expire") || result.equals("invalid")){
            // 재발급 신호 반환
            response.setStatus(400);
            jsonObject.addProperty("msg", "needRefreshToken");
            response.getWriter().write(gson.toJson(jsonObject));
            return false;
        }

        request.setAttribute("userId", Integer.parseInt(result));
        return true;
    }
}
