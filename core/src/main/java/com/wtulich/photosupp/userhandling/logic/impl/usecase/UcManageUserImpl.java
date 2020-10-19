package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.AccountDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.AccountEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.AccountMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.PermissionsMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.RoleMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.UserMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcManageUser;
import com.wtulich.photosupp.userhandling.logic.impl.events.OnRegistrationCompleteEvent;
import com.wtulich.photosupp.userhandling.logic.impl.validator.AccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Collectors;

@Validated
@Named
public class UcManageUserImpl implements UcManageUser {

    private static final Logger LOG = LoggerFactory.getLogger(UcManageUserImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String CREATE_USER_LOG = "Create User with surname {} in database.";
    private static final String UPDATE_USER_LOG = "Update User with id {} in database.";
    private static final String CREATE_ACCOUNT_LOG = "Create Account with username {} in database.";
    private static final String UPDATE_ACCOUNT_LOG = "Update Account with id {} in database.";
    private static final String SEND_EMAIL_FOR_VERIFICATION = "Send mail for verification of username {} in database.";

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
    private RoleMapper roleMapper;

    @Inject
    private PermissionsMapper permissionsMapper;

    @Inject
    private AccountValidator accountValidator;

    @Inject
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public UserEto createUserAndAccountEntities(UserTo userTo, HttpServletRequest request, Errors errors) {
        LOG.debug(CREATE_USER_LOG, userTo.getSurname());
        AccountEntity accountEntity = createAccountEntities(userTo.getAccountTo());
        sendMailOfAccountCreation(accountEntity, request, errors);

        UserEntity userEntity = userMapper.toUserEntity(userTo);
        userEntity.setRole(getRoleById(userTo.getRoleId()));
        userEntity.setAccount(accountEntity);

        return toUserEto(userDao.save(userEntity));
    }

    @Override
    public UserEto updateUser(UserTo userTo, Long userId) {
        LOG.debug(UPDATE_USER_LOG, userId);
        UserEntity userEntity = getUserById(userId);

        userEntity.setRole(getRoleById(userTo.getRoleId()));
        userEntity.setName(userTo.getName());
        userEntity.setSurname(userTo.getSurname());

        return toUserEto(userEntity);
    }

    @Override
    public AccountEto updateUserAccount(AccountTo accountTo, Long userId) {
        LOG.debug(UPDATE_ACCOUNT_LOG, userId);
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
        LOG.debug(CREATE_ACCOUNT_LOG, accountEntity.getUsername());

        return accountDao.save(accountEntity);
    }

    private void verifyAccount(AccountTo accountTo){
        try {
            accountValidator.verifyIfAccountAlreadyExists(accountTo);
            accountValidator.isValidEmailAddress(accountTo.getEmail());
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    private void sendMailOfAccountCreation(AccountEntity accountEntity, HttpServletRequest request, Errors errors) {
        String appUrl = request.getContextPath();

        LOG.debug(SEND_EMAIL_FOR_VERIFICATION, accountEntity.getUsername());
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(accountEntity,
                request.getLocale(), appUrl));
    }


    private AccountEntity toAccountEntity(AccountTo accountTo) {
        String username = extractUsername(accountTo.getEmail());
        AccountEntity accountEntity = accountMapper.toAccountEntity(accountTo);
        accountEntity.setUsername(username);
        accountEntity.setActivated(false);

        return accountEntity;
    }

    private UserEto toUserEto(UserEntity userEntity){
        UserEto userEto = userMapper.toUserEto(userEntity);
        userEto.setAccountEto(accountMapper.toAccountEto(userEntity.getAccount()));

        RoleEto roleEto = roleMapper.toRoleEto(userEntity.getRole());
        roleEto.setPermissionEtoList(userEntity.getRole().getPermissions().stream()
                .map(p -> permissionsMapper.toPermissionEto(p))
                .collect(Collectors.toList()));
        userEto.setRoleEto(roleEto);
        return userEto;
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
