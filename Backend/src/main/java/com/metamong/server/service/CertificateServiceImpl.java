package com.metamong.server.service;

import com.metamong.server.dto.CertificateDto;
import com.metamong.server.dto.EducationDto;
import com.metamong.server.dto.MissionDto;
import com.metamong.server.dto.RankDto;
import com.metamong.server.entity.Certificate;
import com.metamong.server.entity.Education;
import com.metamong.server.entity.User;
import com.metamong.server.exception.ApplicationException;
import com.metamong.server.repository.CertificateRepository;
import com.metamong.server.repository.EducationRepository;
import com.metamong.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private EducationRepository educationRepository;

    public void registerRank(MissionDto.RankRequest rankReq, int userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Education> education = educationRepository.findByEducation(rankReq.getEducation());

        Certificate certificate = new Certificate();
        certificate.setUser(user.get());
        certificate.setEducation(education.get());
        certificate.setCreateAt(new Date());
        certificate.setPassTime(rankReq.getPassTime());

        certificateRepository.save(certificate);
    }

    public RankDto.ResponseList getRank(String education){

        Optional<Education> edu = educationRepository.findByEducation(education);

        List<Certificate> certificateList = certificateRepository.findAllByEducation(edu.get());

        List<RankDto> rankDtoList = new ArrayList<>();
        for (Certificate certificate : certificateList) {
            RankDto rankDto = RankDto.builder()
                    .nickname(certificate.getUser().getNickname())
                    .passTime(certificate.getPassTime())
                    .createAt(certificate.getCreateAt())
                    .build();

            rankDtoList.add(rankDto);
        }

        RankDto.ResponseList responseList = new RankDto.ResponseList();
        responseList.setData(rankDtoList);

        return responseList;
    }

    public void updateCertificate(EducationDto.EduRequest eduReq, int userId){
        Optional<Education> edu = educationRepository.findByEducation(eduReq.getEducation());

        Optional<Certificate> certificate = certificateRepository.findByEducationAndUserId(edu.get(), userId);
        if(!certificate.isPresent()) throw new ApplicationException(HttpStatus.valueOf(404));

        certificate.get().setPassTime(eduReq.getPassTime());
        certificateRepository.save(certificate.get());
    }

    public void registerCertificate(EducationDto.EduRequest eduReq, int userId){
        Optional<Education> edu = educationRepository.findByEducation(eduReq.getEducation());

        if(eduReq.getPassTime() > edu.get().getDuration()) throw new ApplicationException(HttpStatus.valueOf(405));

        Optional<User> user = userRepository.findById(userId);
        Optional<Education> education = educationRepository.findByEducation(eduReq.getEducation());

        Certificate certificate = new Certificate();
        certificate.setUser(user.get());
        certificate.setEducation(education.get());
        certificate.setCreateAt(new Date());
        certificate.setPassTime(eduReq.getPassTime());
        certificate.setAuthenticated(true);

        certificateRepository.save(certificate);
    }

    public EducationDto.EduResponse getCertificate(String education, int userId){
        Optional<Education> edu = educationRepository.findByEducation(education);

        Optional<Certificate> certificate = certificateRepository.findByEducationAndUserId(edu.get(), userId);

        EducationDto.EduResponse eduResponse = new EducationDto.EduResponse();
        eduResponse.setNickname(certificate.get().getUser().getNickname());
        eduResponse.setEducation(certificate.get().getEducation().getEducation());
        eduResponse.setCreateAt(certificate.get().getCreateAt());

        return eduResponse;
    }

}
