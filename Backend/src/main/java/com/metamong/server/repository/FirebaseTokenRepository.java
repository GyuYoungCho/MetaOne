package com.metamong.server.repository;

import com.metamong.server.entity.FirebaseToken;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseTokenRepository extends JpaRepository<FirebaseToken, Integer> {
	Optional<List<FirebaseToken>> findByUserIdAndToken(int userId, String token);
	Optional<List<FirebaseToken>> findByUserId(int userId);
	Optional<List<FirebaseToken>> findByUserIdInOrderByCreateAtDesc(List<Integer> userIds);
	Optional<List<FirebaseToken>> findByCreateAtBefore(LocalDateTime ldt);
	Optional<List<FirebaseToken>> findByToken(String token);
	Optional<List<FirebaseToken>> findByUserIdNot(int userId);
}
