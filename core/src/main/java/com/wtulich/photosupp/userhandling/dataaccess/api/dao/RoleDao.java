package com.wtulich.photosupp.userhandling.dataaccess.api.dao;

import com.wtulich.photosupp.userhandling.dataaccess.api.entity.PermissionEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<RoleEntity, Long> {

    boolean existsByName(String name);
    boolean existsByPermissionsIn(List<PermissionEntity> permissions);
}
