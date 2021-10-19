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
@Alias("Certificate")
public class CertificateDto {
    private Integer id;
    private Integer userId;
    private Integer educationId;
    private Date createAt;
    private Date passTime;
    private boolean is_educated;
    private boolean is_authenticated;

    @JsonIgnore
    private UserDto userDto;
    @JsonIgnore
    private EducationDto educationDto;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private CertificateDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<CertificateDto> data;
    }
}
