package com.metamong.server.service;

import com.metamong.server.dto.CertificateDto;
import com.metamong.server.dto.EducationDto;
import com.metamong.server.dto.MissionDto;
import com.metamong.server.dto.RankDto;
import org.springframework.http.ResponseEntity;

public interface CertificateService {

    public EducationDto.EduResponse getCertificate(String education, int userId);

    public void updateCertificate(EducationDto.EduRequest eduReq, int userId);

    public RankDto.ResponseList getRank(String education);
}
