package com.metamong.server.repository;

import com.metamong.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findById(int id);
    Optional<User> findByIdAndPassword(int id, String password);

}
