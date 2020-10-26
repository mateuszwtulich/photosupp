package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;

public interface UcDeleteRole {

    void deleteRole(Long id) throws EntityDoesNotExistException;
}
