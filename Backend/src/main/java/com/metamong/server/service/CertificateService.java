package com.metamong.server.service;

import com.metamong.server.dto.EducationDto;
import com.metamong.server.dto.RankDto;

public interface CertificateService {

    public EducationDto.EduResponse getCertificate(String education, int userId);

    public void updateCertificate(EducationDto.EduRequest eduReq, int userId);

    public RankDto.ResponseList getRank(String education);

    void setMissionClearTime(int userId, int unityTime, String unityEducation);
}
