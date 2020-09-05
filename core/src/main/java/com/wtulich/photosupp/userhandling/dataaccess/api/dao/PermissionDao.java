package com.wtulich.photosupp.userhandling.dataaccess.api.dao;

import com.wtulich.photosupp.userhandling.dataaccess.api.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<PermissionEntity, Long> {
}
