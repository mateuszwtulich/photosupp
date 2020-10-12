package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;

public interface UcManageAccount {

    AccountEto createAccount(AccountTo accountTo);

    AccountEto updateAccount(AccountTo accountTo);

    AccountEto updateAccountEmailAndUsername(AccountTo accountTo);

    AccountEto updateAccountPassword(AccountTo accountTo);
}
