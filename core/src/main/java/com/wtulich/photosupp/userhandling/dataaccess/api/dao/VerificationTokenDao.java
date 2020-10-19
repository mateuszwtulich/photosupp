package com.wtulich.photosupp.userhandling.dataaccess.api.dao;

import java.util.Optional;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenDao extends JpaRepository<VerificationTokenEntity, Long> {

    Optional<VerificationTokenEntity> findByToken(String token);
}
