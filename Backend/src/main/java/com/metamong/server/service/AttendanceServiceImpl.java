package com.metamong.server.service;

import com.metamong.server.dto.MyAttendDto;
import com.metamong.server.entity.*;
import com.metamong.server.repository.CertificateRepository;
import com.metamong.server.repository.EducationRepository;
import com.metamong.server.repository.UserRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private EducationRepository educationRepository;
    private UserRepository userRepository;
    private CertificateRepository certificateRepository;

    public AttendanceServiceImpl(EducationRepository educationRepository,
                                 UserRepository userRepository,
                                 CertificateRepository certificateRepository){
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
        this.certificateRepository = certificateRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MyAttendDto.ResponseList getMyAttendance(int userId) {

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QUser qUser = QUser.user;
        QCertificate qCertificate = QCertificate.certificate;

        JPAQuery<Tuple> ret = query.from(qUser, qCertificate)
                .where(qUser.id.eq(qCertificate.user.id), qUser.id.eq(userId))
                .orderBy(qCertificate.isAuthenticated.desc(), qCertificate.isEducated.desc(), qCertificate.createAt.desc())
                .select(qUser.nickname, qCertificate.createAt, qCertificate.education.education, qCertificate.isAuthenticated, qCertificate.passTime, qCertificate.isEducated);

        List<Tuple> list = ret.fetch();
        List<MyAttendDto> myAttendDtoList = new ArrayList<>();
        for(Tuple x: list){
            MyAttendDto myAttendDto = MyAttendDto.builder()
                    .nickname(x.get(qUser.nickname))
                    .createAt(x.get(qCertificate.createAt))
                    .education(x.get(qCertificate.education.education))
                    .authenticated(x.get(qCertificate.isAuthenticated))
                    .educated(x.get(qCertificate.isEducated))
                    .passTime(x.get(qCertificate.passTime))
                    .build();

            myAttendDtoList.add(myAttendDto);
        }

        MyAttendDto.ResponseList responseList = new MyAttendDto.ResponseList();
        responseList.setData(myAttendDtoList);

        return responseList;
    }

    @Override
    public void registerAttendance(String education, int userId) {
        Optional<Education> edu = educationRepository.findByEducation(education);
        Optional<User> user = userRepository.findById(userId);

        if(edu.isPresent() && user.isPresent()){
            Certificate certificate = Certificate.builder()
                    .user(user.get())
                    .education(edu.get())
                    .createAt(new Date())
                    .isEducated(true)
                    .isAuthenticated(false)
                    .build();

            certificateRepository.save(certificate);
        }
    }
}
