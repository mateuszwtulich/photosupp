package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;

import java.util.List;

public interface UcFindUser {

    UserEto findUser(Long id);

    List<UserEto> findAllUsers();

    List<UserEto> findAllUsersByRoleId(Long roleId);
}
