package com.metamong.server.repository;

import com.metamong.server.entity.FirebaseToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseTokenRepository extends JpaRepository<FirebaseToken, Integer> {
}
