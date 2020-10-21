package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;

import java.util.List;
import java.util.Optional;

public interface UcFindRole {

    Optional<RoleEto> findRole(Long id);

    Optional<List<RoleEto>> findAllRoles();
}
