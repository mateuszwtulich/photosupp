package com.wtulich.photosupp.userhandling.service.impl.ui;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
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
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserRestServiceImpl implements UserRestService {
    private static final Logger LOG = LoggerFactory.getLogger(UserRestServiceImpl.class);
    private static String USERS_NOT_EXIST = "Users do not exist.";
    private static String ACCOUNTS_NOT_EXIST = "Accounts do not exist.";
    private static String ROLES_NOT_EXIST = "Roles do not exist.";
    private static final String VERIFICATION_TOKEN_NOT_FOUND = "Verification token not found!";

    @Inject
    private UserHandlingImpl userHandling;

    public UserRestServiceImpl() {
    }

    public UserRestServiceImpl(UserHandlingImpl userHandling) {
        this.userHandling = userHandling;
    }

    @Override
    public UserEto getUser(Long id) {
        return userHandling.findUser(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "User with id " + id + " does not exist."));
    }

    @Override
    public List<UserEto> getAllUsers() {
        return userHandling.findAllUsers().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, USERS_NOT_EXIST));
    }

    @Override
    public List<AccountEto> getAllAccounts() {
        return userHandling.findAllAccounts().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, ACCOUNTS_NOT_EXIST));
    }

    @Override
    public List<UserEto> getAllUsersByRoleId(Long roleId) {
        return userHandling.findAllUsersByRoleId(roleId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "Users with role id" + roleId + " do not exist."));
    }

    @Override
    public UserEto createUser(UserTo userTo, HttpServletRequest request, Errors errors) {
        try {
            return userHandling.createUserAndAccountEntities(userTo, request, errors).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @Override
    public RedirectView confirmRegistration(String token) {
        return userHandling.confirmRegistration(token).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, VERIFICATION_TOKEN_NOT_FOUND));
    }

    @Override
    public UserEto updateUser(Long id, UserTo userTo) {
        return userHandling.updateUser(userTo, id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public AccountEto updateUserAccount(Long userId, AccountTo accountTo) {
        try {
            return userHandling.updateUserAccount(accountTo, userId).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        userHandling.deleteUserAndAllRelatedEntities(id);
    }

    @Override
    public RoleEto getRole(Long id) {
        return userHandling.findRole(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "Role with id " + id + " does not exist."));
    }

    @Override
    public List<RoleEto> getAllRoles() {
        return userHandling.findAllRoles().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, ROLES_NOT_EXIST));
    }

    @Override
    public RoleEto createRole(RoleTo roleTo) {
        try {
            return userHandling.createRole(roleTo).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (EntityAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @Override
    public RoleEto updateRole(Long id, RoleTo roleTo) {
        try {
            return userHandling.updateRole(roleTo, id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (EntityAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @Override
    public void deleteRole(Long id) {
        userHandling.deleteRole(id);
    }
}
