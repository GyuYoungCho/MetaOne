package com.metamong.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("FirebaseToken")
public class FirebaseTokenDto {
    private Integer id;
    private Integer userId;
    private String token;
    private Date createAt;

    @JsonIgnore
    private UserDto userDto;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private FirebaseTokenDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<FirebaseTokenDto> data;
    }
}
