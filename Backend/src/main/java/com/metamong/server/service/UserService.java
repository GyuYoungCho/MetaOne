package com.metamong.server.service;

import com.metamong.server.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public int register(UserDto.RegisterRequest registerInfo);
    public boolean isExistEmail(String email);
    public boolean isExistNickname(String nickname);
    public UserDto.LoginRes login(UserDto.LoginRequest loginReq);
    public String TokenGeneration(int userId, String receiverEmail, String url);
    public void validatePassword(String password);
    public void updatePassword(UserDto.UpdateRequest updateInfo, HttpServletRequest request);
    public void updateNickname(UserDto.UpdateRequest updateInfo, HttpServletRequest request);
    public void deleteUser(int id);
}
