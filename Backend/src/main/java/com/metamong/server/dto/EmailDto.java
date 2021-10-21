package com.metamong.server.dto;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "email") // 설정 파일에서 email: 로 시작하는 properties
@Configuration
public class EmailDto {
    private String toEmail;
    private String title;
    private String code;
    private Long validTime;
}
