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
@Alias("Room")
public class RoomDto {
    private Integer id;
    private String name;
    private Integer maxPopulation;

    @JsonIgnore
    private List<UserDto> userDto;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private RoomDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<RoomDto> data;
    }
}
