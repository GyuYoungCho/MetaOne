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
    private String Content;

    @JsonIgnore
    private UserDto userDto;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private GuestBookDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<GuestBookDto> data;
    }
}
