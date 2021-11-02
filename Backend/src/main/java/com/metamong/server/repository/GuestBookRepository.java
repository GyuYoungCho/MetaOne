package com.metamong.server.repository;

import com.metamong.server.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
    Optional<List<GuestBook>> findAllByCreateAtBetween(Date startTime, Date endTime);
}
