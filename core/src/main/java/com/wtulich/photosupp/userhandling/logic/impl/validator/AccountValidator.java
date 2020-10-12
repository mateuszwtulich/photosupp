package com.wtulich.photosupp.userhandling.logic.impl.validator;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.AccountDao;
import com.wtulich.photosupp.userhandling.logic.api.exception.AccountAlreadyExistsException;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AccountValidator {
    private static final Logger LOG = LoggerFactory.getLogger(AccountValidator.class);

    @Inject
    private AccountDao accountDao;

    public void verifyIfAccountAlreadyExists(AccountTo accountTo) throws AccountAlreadyExistsException {
        if(accountDao.existsByEmail(accountTo.getEmail())) {
            LOG.error("Account with email {} already exists", accountTo.getEmail());
            throw new AccountAlreadyExistsException("Account with email " + accountTo.getEmail() + " already exists");
        }
    }
}
