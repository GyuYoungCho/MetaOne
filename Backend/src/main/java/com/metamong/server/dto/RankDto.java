package com.metamong.server.dto;

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
@Alias("Rank")
public class RankDto {
    private String nickname;
    private Integer passTime;
    private Date createAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private RankDto data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseList{
        private List<RankDto> data;
    }
}


