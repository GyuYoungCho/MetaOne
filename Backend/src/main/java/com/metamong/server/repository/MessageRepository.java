package com.metamong.server.repository;

import com.metamong.server.entity.Message;
import com.metamong.server.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	Optional<List<Message>> findByRecvUserId(User userId);
	Optional<List<Message>> findBySentUserIdAndRecvUserId(User user1, User user2);
}
