package com.wtulich.photosupp.userhandling.user.dataaccess.api.dao;

import com.wtulich.photosupp.userhandling.user.dataaccess.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long> {
}
