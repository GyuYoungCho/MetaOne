package com.metamong.server.repository;

import com.metamong.server.entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharactersRepository extends JpaRepository<Characters, Integer> {
    Optional<Characters> findById(int cid);
}
