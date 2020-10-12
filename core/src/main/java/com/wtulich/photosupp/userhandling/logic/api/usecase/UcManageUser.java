package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;

public interface UcManageUser {

    UserEto createUser(UserTo userTo);

    UserEto updateUser(UserTo userTo);

    UserEto updateUserNameAndSurname(UserTo userTo);

    UserEto updateUserRole(UserTo userTo);
}
