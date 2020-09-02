package com.wtulich.photosupp.userhandling.user.dataaccess.api.dao;

import com.wtulich.photosupp.userhandling.user.dataaccess.api.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenDao extends JpaRepository<VerificationTokenEntity, Long> {
}
