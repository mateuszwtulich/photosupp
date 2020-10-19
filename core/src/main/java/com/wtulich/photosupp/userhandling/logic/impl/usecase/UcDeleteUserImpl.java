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
import javax.inject.Named;

@Validated
@Named
public class UcDeleteUserImpl implements UcDeleteUser {

    private static final Logger LOG = LoggerFactory.getLogger(UcDeleteUserImpl.class);
    private static final String DELETE_USER_LOG = "Delete User with id {} in database.";
    private static final String DELETE_ACCOUNT_LOG = "Delete Account with id {} in database.";

    @Inject
    private UserDao userDao;

    @Override
    public void deleteUserAndAllRelatedEntities(Long userId) {
        UserEntity userEntity = userDao.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " does not exist."));

        LOG.debug(DELETE_USER_LOG, userId);
        LOG.debug(DELETE_ACCOUNT_LOG, userEntity.getAccount().getId());

        userDao.deleteById(userId);
    }
}
