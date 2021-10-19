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
@Alias("OauthProvider")
public class OauthProviderDto {
    private Integer id;
    private Integer userId;
    private String providerName;

    @JsonIgnore
    private UserDto userDto;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private OauthProviderDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<OauthProviderDto> data;
    }
}
