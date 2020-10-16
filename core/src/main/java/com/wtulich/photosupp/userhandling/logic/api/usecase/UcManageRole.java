package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;

public interface UcManageRole {

    RoleEto createRole(RoleTo roleTo);

    RoleEto updateRole(RoleTo roleTo, Long id);
}
