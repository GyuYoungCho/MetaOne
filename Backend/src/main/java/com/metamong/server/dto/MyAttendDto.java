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
@Alias("MyAttend")
public class MyAttendDto {
    private String nickname;
    private Date createAt;
    private String education;
    private boolean authenticated;
    private Integer passTime;
    private boolean educated;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private MyAttendDto data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseList{
        private List<MyAttendDto> data;
    }
}
