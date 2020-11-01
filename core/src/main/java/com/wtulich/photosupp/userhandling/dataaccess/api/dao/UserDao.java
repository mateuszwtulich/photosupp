package com.wtulich.photosupp.userhandling.dataaccess.api.dao;

import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserDao extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByRole_Id(Long roleId);
}
