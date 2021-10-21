package com.metamong.server.repository;

import com.metamong.server.entity.Certificate;
import com.metamong.server.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    Optional<Certificate> findByEducationAndUserId(Education education, int userId);

    List<Certificate> findAllByEducation(Education education);
}
