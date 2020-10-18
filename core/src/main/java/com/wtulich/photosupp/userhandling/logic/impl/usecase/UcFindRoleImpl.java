package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.PermissionsMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.RoleMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Named
public class UcFindRoleImpl implements UcFindRole {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindRoleImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";

    @Inject
    private RoleDao roleDao;

    @Inject
    private RoleMapper roleMapper;

    @Inject
    private PermissionsMapper permissionsMapper;

    @Override
    public RoleEto findRole(Long id) {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);

        LOG.debug("Get Role with id {} from database.", id);
        RoleEntity roleEntity = roleDao.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "Role with id " + id + " does not exist."));
        return toRoleEto(roleEntity);
    }

    @Override
    public List<RoleEto>  findAllRoles() {
        LOG.debug("Get all Roles from database.");
        Optional<List<RoleEntity>> rolesList = Optional.of(roleDao.findAll());

        return rolesList.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT)).stream()
                .map(roleEntity -> toRoleEto(roleEntity))
                .collect(Collectors.toList());
    }

    private RoleEto toRoleEto(RoleEntity roleEntity){
        RoleEto roleEto = roleMapper.toRoleEto(roleEntity);
        roleEto.setPermissionEtoList(roleEntity.getPermissions().stream()
                .map(p -> permissionsMapper.toPermissionEto(p))
                .collect(Collectors.toList()));
        return roleEto;
    }
}
