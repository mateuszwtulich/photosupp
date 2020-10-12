package com.wtulich.photosupp.userhandling.logic.impl.validator;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.PermissionDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.PermissionEntity;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class RoleValidator {
    private static final Logger LOG = LoggerFactory.getLogger(RoleValidator.class);

    @Inject
    private RoleDao roleDao;

    @Inject
    private PermissionDao permissionDao;

    public void verifyIfRoleAlreadyExists(RoleTo roleTo) throws EntityAlreadyExistsException {
        List<PermissionEntity> permissionList = permissionDao.findAllById(roleTo.getPermissionIds());
        verifyIfRoleNameAlreadyExists(roleTo.getName());
        verifyIfRolePermissionsAlreadyExists(permissionList);
    }

    private void verifyIfRoleNameAlreadyExists(String name) throws EntityAlreadyExistsException {
        if (roleDao.existsByName(name)) {
            LOG.error("Role with name {} already exists", name);
            throw new EntityAlreadyExistsException("Role with name " + name + " already exists");
        }
    }

    private void verifyIfRolePermissionsAlreadyExists(List<PermissionEntity> permissions) throws EntityAlreadyExistsException {
        if (roleDao.existsByPermissionsIn(permissions)) {
            LOG.error("Role with permissions already exists");
            throw new EntityAlreadyExistsException("Role with permissions already exists");
        }
    }
}
