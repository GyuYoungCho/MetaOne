package com.metamong.server.service;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${token.expire.accesstoken}")
    private Long ACCESS_TOKEN_EXP_TIME;

    @Value("${token.expire.refreshtoken}")
    private Long REFRESH_TOKEN_EXP_TIME;

    @Value("${token.secretkey}")
    private String SECRET_KEY;

    @Value("${token.accesstoken}")
    private String ACCESS_TOKEN;

    @Value("${token.refreshtoken}")
    private String REFRESH_TOKEN;

    /**
     * 로그인 성공 시 토큰 생성 및 반환
     * @param userId : 유저 ID
     * @return : Access Token, Refresh Token
     */
    @Transactional
    @Override
    public synchronized Map<String, Object> createToken(int userId) {
        String accessToken = createToken(userId, ACCESS_TOKEN_EXP_TIME, SECRET_KEY);
        String refreshToken = createToken(userId, REFRESH_TOKEN_EXP_TIME, SECRET_KEY);

        Map<String ,Object> map = new HashMap<>();

        map.put(ACCESS_TOKEN, accessToken);
        map.put(REFRESH_TOKEN, refreshToken);
        return map;
    }

    /**
     * JWT를 받아서 해독 후 유저 ID 반환
     * @param token
     * @param secretKey
     * @return : User ID or Except Message
     */
    @Override
    public String decodeToken(String token, String secretKey) {
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }
        catch(ExpiredJwtException e1){
            return "expire";
        }
        catch(JwtException e2){
            return "invalid";
        }
    }


    private String createToken(int userId, Long expTime, String secretKey) {
        Key signingKey = makeKey(secretKey);

        Date expireTime = new Date(System.currentTimeMillis() + expTime);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .setExpiration(expireTime).compact();
    }

    private Key makeKey(String secretKey){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;         // HS256 암호화 알고리즘 사용
        byte[] securityByte = DatatypeConverter.parseBase64Binary(secretKey);     // String 형태의 키를 Byte 형태로 인코딩

        return new SecretKeySpec(securityByte, signatureAlgorithm.getJcaName());   // 암호화된 Key 생성
    }


}
