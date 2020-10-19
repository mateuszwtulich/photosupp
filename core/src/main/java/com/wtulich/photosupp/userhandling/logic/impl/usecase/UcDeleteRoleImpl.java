package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcDeleteRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import javax.inject.Named;

@Validated
@Named
public class UcDeleteRoleImpl implements UcDeleteRole {

    private static final Logger LOG = LoggerFactory.getLogger(UcDeleteRoleImpl.class);
    private static final String DELETE_ROLE_LOG = "Delete Role with id {} in database.";

    @Inject
    private RoleDao roleDao;

    @Override
    public void deleteRole(Long id) {
        RoleEntity roleEntity = roleDao.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with id " + id + " does not exist."));
        LOG.debug(DELETE_ROLE_LOG, roleEntity.getId());

        roleDao.deleteById(roleEntity.getId());
    }
}
