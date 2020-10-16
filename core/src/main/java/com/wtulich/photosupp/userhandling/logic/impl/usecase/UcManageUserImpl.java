package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.AccountDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.AccountEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import com.wtulich.photosupp.userhandling.logic.api.exception.AccountAlreadyExistsException;
import com.wtulich.photosupp.userhandling.logic.api.mapper.AccountMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.UserMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcManageUser;
import com.wtulich.photosupp.userhandling.logic.impl.validator.AccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import java.util.Objects;

@Validated
public class UcManageUserImpl implements UcManageUser {
    private static final Logger LOG = LoggerFactory.getLogger(UcManageUserImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";

    @Inject
    private UserDao userDao;

    @Inject
    private AccountDao accountDao;

    @Inject
    private RoleDao roleDao;

    @Inject
    private UserMapper userMapper;

    @Inject
    private AccountMapper accountMapper;

    @Inject
    private AccountValidator accountValidator;

    @Override
    public UserEto createUserAndAccountEntities(UserTo userTo) {
        LOG.debug("Create User with surname {} in database.", userTo.getSurname());
        AccountEntity accountEntity = createAccountEntities(userTo.getAccountTo());

        UserEntity userEntity = userMapper.toUserEntity(userTo);
        userEntity.setRole(getRoleById(userTo.getRoleId()));
        userEntity.setAccount(accountEntity);

        return userMapper.toUserEto(userDao.save(userEntity));
    }

    @Override
    public UserEto updateUser(UserTo userTo, Long userId) {
        UserEntity userEntity = getUserById(userId);

        userEntity.setRole(getRoleById(userTo.getRoleId()));
        userEntity.setName(userTo.getName());
        userEntity.setSurname(userTo.getSurname());

        return userMapper.toUserEto(userEntity);
    }

    @Override
    public AccountEto updateUserAccount(AccountTo accountTo, Long userId) {
        UserEntity userEntity = getUserById(userId);

        AccountEntity accountEntity = userEntity.getAccount();
        accountEntity.setPassword(accountTo.getPassword());
        accountEntity.setEmail(accountTo.getEmail());
        accountEntity.setUsername(extractUsername(accountTo.getEmail()));

        userEntity.setAccount(accountEntity);
        return accountMapper.toAccountEto(accountEntity);
    }

    private AccountEntity createAccountEntities(AccountTo accountTo) {
        verifyAccount(accountTo);

        AccountEntity accountEntity = toAccountEntity(accountTo);
        LOG.debug("Create Account with username {} in database.", accountEntity.getUsername());

        return accountDao.save(accountEntity);
    }

    private void verifyAccount(AccountTo accountTo){
        try {
            accountValidator.verifyIfAccountAlreadyExists(accountTo);
        } catch (AccountAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    private AccountEntity toAccountEntity(AccountTo accountTo) {
        String username = extractUsername(accountTo.getEmail());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(username);
        accountEntity.setActivated(false);
        accountEntity.setEmail(accountTo.getEmail());
        accountEntity.setPassword(accountTo.getPassword());

        return accountEntity;
    }

    private UserEntity getUserById(Long userId){
        Objects.requireNonNull(userId, ID_CANNOT_BE_NULL);

        return userDao.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " does not exist."));
    }

    private RoleEntity getRoleById(Long roleId){
        Objects.requireNonNull(roleId, ID_CANNOT_BE_NULL);

        return roleDao.findById(roleId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with id " + roleId + " does not exist."));
    }

    private String extractUsername(String email){
        return email.split("@")[0];
    }
}
