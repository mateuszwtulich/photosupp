package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcDeleteRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;

@Validated
public class UcDeleteRoleImpl implements UcDeleteRole {

    private static final Logger LOG = LoggerFactory.getLogger(UcDeleteRoleImpl.class);

    @Inject
    private RoleDao roleDao;

    @Override
    public void deleteRole(Long id) {
        if(roleDao.findById(id).isPresent()){
            LOG.debug("Delete Role with id {} from database.", id);
            roleDao.deleteById(id);
        }
    }
}
