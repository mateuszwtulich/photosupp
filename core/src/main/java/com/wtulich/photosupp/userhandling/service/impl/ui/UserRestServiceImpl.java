package com.wtulich.photosupp.userhandling.service.impl.ui;

import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;
import com.wtulich.photosupp.userhandling.logic.impl.UserHandlingImpl;
import com.wtulich.photosupp.userhandling.service.api.ui.UserRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;


import javax.inject.Inject;
import java.util.List;

@RestController
public class UserRestServiceImpl implements UserRestService {
    private static final Logger LOG = LoggerFactory.getLogger(UserRestServiceImpl.class);

    @Inject
    private UserHandlingImpl userHandling;

    @Override
    public UserEto getUser(Long id) {
        return userHandling.findUser(id);
    }

    @Override
    public List<UserEto> getAllUsers() {
        return userHandling.findAllUsers();
    }

    @Override
    public List<AccountEto> getAllAccounts() {
        return userHandling.findAllAccounts();
    }

    @Override
    public List<UserEto> getAllUsersByRoleId(Long roleId) {
        return userHandling.findAllUsersByRoleId(roleId);
    }

    @Override
    public UserEto createUser(UserTo userTo) {
        return userHandling.createUserAndAccountEntities(userTo);
    }

    @Override
    public UserEto updateUser(Long id, UserTo userTo) {
        return userHandling.updateUser(userTo, id);
    }

    @Override
    public AccountEto updateUserAccount(Long userId, AccountTo accountTo) {
        return userHandling.updateUserAccount(accountTo, userId);
    }

    @Override
    public void deleteUser(Long id) {
        deleteUser(id);
    }

    @Override
    public RoleEto getRole(Long id) {
        return userHandling.findRole(id);
    }

    @Override
    public List<RoleEto> getAllRoles() {
        return userHandling.findAllRoles();
    }

    @Override
    public RoleEto createRole(RoleTo roleTo) {
        return userHandling.createRole(roleTo);
    }

    @Override
    public RoleEto updateRole(Long id, RoleTo roleTo) {
        return userHandling.updateRole(roleTo, id);
    }

    @Override
    public void deleteRole(Long id) {
        userHandling.deleteRole(id);
    }
}
