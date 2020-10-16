package com.wtulich.photosupp.userhandling.logic.impl;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.userhandling.logic.api.UserHandling;
import com.wtulich.photosupp.userhandling.logic.api.exception.AccountAlreadyExistsException;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcDeleteRole;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcDeleteUser;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindAccount;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindRole;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindUser;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcManageRegistration;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcManageRole;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcManageUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class UserHandlingImpl implements UserHandling {
    private static final Logger LOG = LoggerFactory.getLogger(UserHandlingImpl.class);
    //For pagination of users

    @Inject
    private UcDeleteRole ucDeleteRole;

    @Inject
    private UcDeleteUser ucDeleteUser;

    @Inject
    private UcFindAccount ucFindAccount;

    @Inject
    private UcFindRole ucFindRole;

    @Inject
    private UcFindUser ucFindUser;

    @Inject
    private UcManageRegistration ucManageRegistration;

    @Inject
    private UcManageRole ucManageRole;

    @Inject
    private UcManageUser ucManageUser;

    @Override
    public void deleteRole(Long id) {
        ucDeleteRole.deleteRole(id);
    }

    @Override
    public void deleteUserAndAllRelatedEntities(Long userId) {
        ucDeleteUser.deleteUserAndAllRelatedEntities(userId);
    }

    @Override
    public List<AccountEto> findAllAccounts() {
        return ucFindAccount.findAllAccounts();
    }

    @Override
    public RoleEto findRole(Long id) {
        return ucFindRole.findRole(id);
    }

    @Override
    public List<RoleEto> findAllRoles() {
        return ucFindRole.findAllRoles();
    }

    @Override
    public UserEto findUser(Long id) {
        return ucFindUser.findUser(id);
    }

    @Override
    public List<UserEto> findAllUsers() {
        return ucFindUser.findAllUsers();
    }

    @Override
    public List<UserEto> findAllUsersByRoleId(Long roleId) {
        return ucFindUser.findAllUsersByRoleId(roleId);
    }

    @Override
    public RoleEto createRole(RoleTo roleTo) {
        return ucManageRole.createRole(roleTo);
    }

    @Override
    public RoleEto updateRole(RoleTo roleTo, Long id) {
        return ucManageRole.updateRole(roleTo, id);
    }

    @Override
    public UserEto createUserAndAccountEntities(UserTo userTo) {
        return ucManageUser.createUserAndAccountEntities(userTo);
    }

    @Override
    public UserEto updateUser(UserTo userTo, Long userId) {
        return ucManageUser.updateUser(userTo, userId);
    }

    @Override
    public AccountEto updateUserAccount(AccountTo accountTo, Long userId) {
        return ucManageUser.updateUserAccount(accountTo, userId);
    }
}
