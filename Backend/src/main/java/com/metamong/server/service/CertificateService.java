package com.metamong.server.service;

import com.metamong.server.dto.RankDto;

public interface CertificateService {

    public RankDto.ResponseList getRank(String education);

    void setMissionClearTime(int userId, int unityTime, String unityEducation);
}
