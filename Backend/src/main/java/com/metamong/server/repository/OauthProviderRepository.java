package com.metamong.server.repository;

import com.metamong.server.entity.OauthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthProviderRepository extends JpaRepository<OauthProvider, Integer> {
}
