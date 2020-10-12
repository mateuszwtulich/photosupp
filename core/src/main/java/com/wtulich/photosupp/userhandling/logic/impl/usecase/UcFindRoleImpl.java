package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.RoleMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Validated
public class UcFindRoleImpl implements UcFindRole {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindRoleImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";

    @Inject
    private RoleDao roleDao;

    @Inject
    private RoleMapper roleMapper;

    @Override
    public RoleEto findRole(Long id) {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);

        LOG.debug("Get Role with id {} from database.", id);
        RoleEntity roleEntity = roleDao.findById(id).get();
        return roleMapper.toRoleEto(roleEntity);
    }

    @Override
    public List<RoleEto> findAllRoles() {
        LOG.debug("Get all Roles from database.");
        return roleDao.findAll().stream()
                .map(roleEntity -> roleMapper.toRoleEto(roleEntity))
                .collect(Collectors.toList());
    }
}
