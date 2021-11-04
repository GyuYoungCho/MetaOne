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
@Alias("GuestBook")
public class GuestBookDto {
    private Integer id;
    private Integer userId;
    private Date createAt;
    private String content;
    private String nickname;

    @JsonIgnore
    private UserDto userDto;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GuestBookReq{
    	private String content;
    }
    
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private GuestBookDto data;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseList{
        private List<GuestBookDto> data;
    }
}
