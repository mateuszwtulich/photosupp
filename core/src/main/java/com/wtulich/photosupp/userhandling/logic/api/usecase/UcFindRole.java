package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;

import java.util.List;

public interface UcFindRole {

    RoleEto findRole(Long id);

    List<RoleEto> findAllRoles();
}
