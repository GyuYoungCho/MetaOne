package com.metamong.server.service;

import java.util.Map;

public interface JwtService {
    public Map<String, Object> createToken(int userId);

    public String decodeToken(String token, String secretKey);
}
