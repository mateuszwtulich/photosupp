package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;

public interface UcManageUser {

    UserEto createUserAndAccountEntities(UserTo userTo);

    UserEto updateUser(UserTo userTo, Long userId);

    AccountEto updateUserAccount(AccountTo accountTo, Long userId);
}
