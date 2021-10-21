package com.metamong.server.repository;

import com.metamong.server.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
    Optional<Education> findByEducation(String education);
}
