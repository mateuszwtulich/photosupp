package com.wtulich.photosupp.userhandling.logic.api.usecase;

import java.util.Optional;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;

import java.util.List;

public interface UcFindUser {

    Optional<UserEto> findUser(Long id);

    Optional<List<UserEto>> findAllUsers();

    Optional<List<UserEto>> findAllUsersByRoleId(Long roleId);
}
