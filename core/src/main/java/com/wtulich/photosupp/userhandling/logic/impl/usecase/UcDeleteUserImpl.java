package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.AccountDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcDeleteUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;

@Validated
public class UcDeleteUserImpl implements UcDeleteUser {

    private static final Logger LOG = LoggerFactory.getLogger(UcDeleteUserImpl.class);

    @Inject
    private UserDao userDao;

    @Inject
    private AccountDao accountDao;

    @Override
    public void deleteUserAndAllRelatedEntities(Long userId) {
        UserEntity userEntity = userDao.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " does not exist."));
        deleteUserAccountEntity(userEntity.getAccount().getId());
        deleteUserEntity(userId);
    }

    private void deleteUserAccountEntity(Long accountId){
        LOG.debug("Delete Account with id {} from database.", accountId);
        accountDao.deleteById(accountId);
    }

    private void deleteUserEntity(Long userId){
        LOG.debug("Delete User with id {} from database.", userId);
        userDao.deleteById(userId);
    }
}
