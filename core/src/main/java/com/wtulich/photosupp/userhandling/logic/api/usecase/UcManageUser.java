package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

public interface UcManageUser {

    UserEto createUserAndAccountEntities(UserTo userTo, HttpServletRequest request, Errors errors);

    UserEto updateUser(UserTo userTo, Long userId);

    AccountEto updateUserAccount(AccountTo accountTo, Long userId);
}
