package com.metamong.server.repository;

import com.metamong.server.entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository<Characters, Integer> {
}
