package com.metamong.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("Message")
public class MessageDto {
    private Integer id;
    private String title;
    private String content;
    private Date createAt;
    private boolean isRead;
    private Integer sentUserId;
    private Integer recvUserId;

    @JsonIgnore
    private UserDto sentUser;
    @JsonIgnore
    private UserDto recvUser;
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MRegisterRequest{
    	
    	private String nickname;
    	private String title;
    	private String content;
    	private String firebaseToken;
    }
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AllSendRequest{
    	@NotNull
    	private String title;
    	@NotNull
    	private String content;
    }

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
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyMessageResponse{
    	private int id;
        private boolean isRead;
        private String nickname;
        private Date createAt;
        private String title;
        private String content;
    }
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OneMessageResponse{
    	private int id;
    	private boolean whose;
    	private boolean isRead;
        private String nickname;
        private Date createAt;
        private String title;
    	private String content;
    }
}
