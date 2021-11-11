package com.metamong.server.service;

import com.metamong.server.dto.EducationDto;
import com.metamong.server.dto.RankDto;
import com.metamong.server.entity.Certificate;
import com.metamong.server.entity.Education;
import com.metamong.server.exception.ApplicationException;
import com.metamong.server.repository.CertificateRepository;
import com.metamong.server.repository.EducationRepository;
import com.metamong.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CertificateServiceImpl implements CertificateService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private EducationRepository educationRepository;

    public EducationDto.EduResponse getCertificate(String education, int userId){
        Optional<Education> edu = educationRepository.findByEducation(education);

        Optional<Certificate> certificate = certificateRepository.findByEducationAndUserId(edu.get(), userId);
        if(!certificate.isPresent()) throw new ApplicationException(HttpStatus.valueOf(404), "There is no certification");

        EducationDto.EduResponse eduResponse = new EducationDto.EduResponse();
        eduResponse.setNickname(certificate.get().getUser().getNickname());
        eduResponse.setEducation(certificate.get().getEducation().getEducation());
        eduResponse.setCreateAt(certificate.get().getCreateAt());

        return eduResponse;
    }

    public void updateCertificate(EducationDto.EduRequest eduReq, int userId){
        Optional<Education> edu = educationRepository.findByEducation(eduReq.getEducation());

        Optional<Certificate> certificate = certificateRepository.findByEducationAndUserId(edu.get(), userId);
        if(!certificate.isPresent()) throw new ApplicationException(HttpStatus.valueOf(404), "There is no certification");

        if(certificate.get().getPassTime() == null){
            if(eduReq.getPassTime() > edu.get().getDuration()) throw new ApplicationException(HttpStatus.valueOf(404), "TIME OUT");
            certificate.get().setPassTime(eduReq.getPassTime());
            certificateRepository.save(certificate.get());
        }

        else if(eduReq.getPassTime() < certificate.get().getPassTime()){
            certificate.get().setPassTime(eduReq.getPassTime());
            certificateRepository.save(certificate.get());
        }
    }

    public RankDto.ResponseList getRank(String education){
        String educationName = convertEducationName(education);

        Optional<Education> edu = educationRepository.findByEducation(educationName);

        List<Certificate> certificateList = certificateRepository.findAllByEducation(edu.get());

        List<RankDto> rankDtoList = new ArrayList<>();
        for (Certificate certificate : certificateList) {
            if(certificate.getPassTime() == null) continue;         // 교육만 들은 상태는 배제
            RankDto rankDto = RankDto.builder()
                    .nickname(certificate.getUser().getNickname())
                    .passTime(certificate.getPassTime())
                    .createAt(certificate.getCreateAt())
                    .build();

            rankDtoList.add(rankDto);
        }
        Collections.sort(rankDtoList, (o1, o2) -> {
            return o1.getPassTime() - o2.getPassTime();
        });
        rankDtoList = rankDtoList.subList(0, Math.min(5, rankDtoList.size()));
        RankDto.ResponseList responseList = new RankDto.ResponseList();
        responseList.setData(rankDtoList);

        return responseList;
    }

    @Override
    public void setMissionClearTime(int userId, int unityTime, String education) {

        String educationName = convertEducationName(education);
        Optional<Education> edu = educationRepository.findByEducation(educationName);

        Optional<Certificate> certificate = certificateRepository.findByEducationAndUserId(edu.get(), userId);

        certificate.ifPresent(select -> {
            select.setPassTime(Math.min(select.getPassTime(), unityTime));

            certificateRepository.save(select);
        });
    }

    @Override
    public void setEducationAuth(int userId, int auth, String education) {
        String educationName = convertEducationName(education);
        Optional<Education> edu = educationRepository.findByEducation(educationName);

        Optional<Certificate> certificate = certificateRepository.findByEducationAndUserId(edu.get(), userId);

        certificate.ifPresent(select -> {
            select.setAuthenticated(auth == 1);

            certificateRepository.save(select);
        });
    }

    private String convertEducationName(String education){
        String educationName = null;
        switch (education) {
            case "fire":
                educationName = "화재";
                break;
            case "earthquake":
                educationName = "지진";
                break;
            case "typhoon":
                educationName = "태풍";
                break;
            case "corona":
                educationName = "코로나";
                break;
        }

        return educationName;
    }
}
