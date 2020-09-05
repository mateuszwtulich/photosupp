package com.wtulich.photosupp.userhandling.dataaccess.api.dao;

import com.wtulich.photosupp.userhandling.dataaccess.api.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<AccountEntity, Long> {
}
