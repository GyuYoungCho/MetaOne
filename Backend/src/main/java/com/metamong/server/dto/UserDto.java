package com.metamong.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("User")
public class UserDto {
    private Integer id;
    private Integer roomId;
    private String email;
    private String password;
    private String fileUrl;
    private boolean auth;
    private String nickname;
    private String name;
    private boolean state;

    @JsonIgnore
    private RoomDto roomDto;
    @JsonIgnore
    private List<FirebaseTokenDto> firebaseTokenDto;
    @JsonIgnore
    private List<OauthProviderDto> oauthProviderDto;
    @JsonIgnore
    private List<GuestBookDto> guestBookDto;
    @JsonIgnore
    private List<CertificateDto> certificateDto;
    @JsonIgnore
    private List<MessageDto> messageDto;


    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private UserDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<UserDto> data;
    }

}
