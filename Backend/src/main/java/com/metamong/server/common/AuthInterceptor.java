package com.metamong.server.common;

import com.metamong.server.service.JwtService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        // JWT 사용자 인증 로직
//        System.out.println(refreshToken);
//        System.out.println(accessToken);
        String acToken = request.getHeader(accessToken);
        String result = jwtService.decodeToken(acToken, secretkey);
        if (result.equals("expire")){
            //재발급
        }else if(result.equals("invalid")){
            return false;
        }else{
            //정상
            request.setAttribute("userId",Integer.parseInt(result));
        }
        return true;
    }
}
