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
@Alias("Education")
public class EducationDto {
    private Integer id;
    private Date duration;
    private String education;

    @JsonIgnore
    private List<CertificateDto> certificateDto;
    @JsonIgnore
    private List<MissionDto> missionDto;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private EducationDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<EducationDto> data;
    }
}
