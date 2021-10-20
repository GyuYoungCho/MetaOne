package com.metamong.server.repository;

import com.metamong.server.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
}
