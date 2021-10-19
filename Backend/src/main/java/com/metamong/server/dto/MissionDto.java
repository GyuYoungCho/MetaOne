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
@Alias("Mission")
public class MissionDto {
    private Integer id;
    private Integer ordering;
    private String description;
    private Integer educationId;

    @JsonIgnore
    private EducationDto educationDto;

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private MissionDto data;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private List<MissionDto> data;
    }
}
