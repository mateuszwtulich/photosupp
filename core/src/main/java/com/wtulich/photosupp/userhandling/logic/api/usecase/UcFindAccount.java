package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;

import java.util.List;

public interface UcFindAccount {

    List<AccountEto> findAllAccounts();
}
