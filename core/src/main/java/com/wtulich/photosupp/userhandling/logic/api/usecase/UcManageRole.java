package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;

public interface UcManageRole {

    RoleEto createRole(RoleTo roleTo) throws EntityAlreadyExistsException;

    RoleEto updateRole(RoleTo roleTo, Long id) throws EntityAlreadyExistsException;
}
