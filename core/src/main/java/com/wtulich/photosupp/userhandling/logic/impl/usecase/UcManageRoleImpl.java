package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.PermissionDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.PermissionEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.RoleMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcManageRole;
import com.wtulich.photosupp.userhandling.logic.impl.validator.RoleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Validated
public class UcManageRoleImpl implements UcManageRole {
    private static final Logger LOG = LoggerFactory.getLogger(UcManageRoleImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";

    @Inject
    private RoleDao roleDao;

    @Inject
    private PermissionDao permissionDao;

    @Inject
    private RoleMapper roleMapper;

    @Inject
    private RoleValidator roleValidator;

    @Override
    public RoleEto createRole(RoleTo roleTo) {
        verifyRole(roleTo);
        LOG.debug("Create Role with name {} in database.", roleTo.getName());

        RoleEntity roleEntity = roleMapper.toRoleEntity(roleTo);
        roleEntity.setPermissions(getPermissionsByIds(roleTo.getPermissionIds()));
        return roleMapper.toRoleEto(roleDao.save(roleEntity));
    }

    @Override
    public RoleEto updateRole(RoleTo roleTo, Long id) {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);

        RoleEntity roleEntity = roleDao.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with id " + id + " does not exist."));

        verifyRole(roleTo);
        LOG.debug("Update Role with id {} from database.", id);

        return roleMapper.toRoleEto(mapRoleEntity(roleEntity, roleTo));
    }

    private void verifyRole(RoleTo roleTo){
        try {
            roleValidator.verifyIfRoleAlreadyExists(roleTo);
        } catch (EntityAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    private RoleEntity mapRoleEntity(RoleEntity roleEntity, RoleTo roleTo){
        roleEntity.setName(roleTo.getName());
        roleEntity.setDescription(roleTo.getDescription());
        roleEntity.setPermissions(getPermissionsByIds(roleTo.getPermissionIds()));
        return roleEntity;
    }

    private List<PermissionEntity> getPermissionsByIds(List<Long> permissionIds){
        return permissionDao.findAllById(permissionIds);
    }
}
