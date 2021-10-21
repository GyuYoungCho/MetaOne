package com.metamong.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
//        private UserDto data;
        private Integer id;
        private String email;
        private String nickname;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest{

        @NotNull
        private String name;

        @NotNull
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
        private String password;

        @NotNull
        private  String email;

        @NotNull
        @Length(min = 1, max = 10)
        private String nickname;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateRequest{
        @NotNull
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
        private String originPassword;

        private String newPassword;
        private String newPasswordCheck;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest{
        private String email;
        private String password;
        private  String firebaseToken;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TokenRequest {                // 요청
        private Integer id;
        private String email;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<UserDto> data;
    }

}
