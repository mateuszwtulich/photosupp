package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.AccountDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.AccountEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.AccountMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
public class UcFindAccountImpl implements UcFindAccount {
    private static final Logger LOG = LoggerFactory.getLogger(UcFindAccountImpl.class);

    @Inject
    private AccountDao accountDao;

    @Inject
    private AccountMapper accountMapper;

    @Override
    public List<AccountEto> findAllAccounts() {
        LOG.debug("Get all Accounts from database.");
        Optional<List<AccountEntity>> accountsList = Optional.of(accountDao.findAll());

        return accountsList.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT)).stream()
                .map(accountEntity -> accountMapper.toAccountEto(accountEntity))
                .collect(Collectors.toList());
    }
}
