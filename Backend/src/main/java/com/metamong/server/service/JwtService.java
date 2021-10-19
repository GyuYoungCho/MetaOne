package com.metamong.server.service;

import java.util.Map;

public interface JwtService {
    Map<String, Object> createToken(int userId);

    String decodeToken(String token, String secretKey);
}
