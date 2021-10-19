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
@Alias("Message")
public class MessageDto {
    private Integer id;
    private String content;
    private Date createAt;
    private boolean isRead;
    private Integer sentUserId;
    private Integer recvUserId;

    @JsonIgnore
    private UserDto sentUser;
    @JsonIgnore
    private UserDto recvUser;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private MessageDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<MessageDto> data;
    }
}
