package com.metamong.server.service;

import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.Characters;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public int register(UserDto.RegisterRequest registerInfo);
    public boolean isExistEmail(String email);
    public boolean isExistNickname(String nickname);
    public UserDto.Response login(String email);
    public String TokenGeneration(int userId, String receiverEmail, String url);
    public void kakaoRegister(String email, String name);
    public UserDto.userInfoResponse getUserInfo(String nickname);
    public void setCharacter(int userId, int character);
    public UserDto.characterResponse getCharacter(int userId);
    public UserDto.allCharactersResponse getAllCharacter();
    public UserDto.LoginRes login(UserDto.LoginRequest loginReq);
    public void validatePassword(String password);
    public void updatePassword(UserDto.UpdateRequest updateInfo, HttpServletRequest request);
    public void updateNickname(UserDto.UpdateRequest updateInfo, HttpServletRequest request);
    public void deleteUser(int id);

}
