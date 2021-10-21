package com.metamong.server.service;

import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.Characters;

public interface UserService {
    public int register(UserDto.RegisterRequest registerInfo);
    public boolean isExistEmail(String email);
    public boolean isExistNickname(String nickname);
    public UserDto.Response login(UserDto.LoginRequest loginReq);
    public UserDto.Response login(String email);
    public String TokenGeneration(int userId, String receiverEmail, String url);
    public void kakaoRegister(String email, String name);
    public UserDto.userInfoResponse getUserInfo(String nickname);
    public UserDto.characterResponse getCharacter(int userId);

}
