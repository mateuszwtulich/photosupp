package com.wtulich.photosupp.serviceordering.logic.api.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;

public interface UcDeleteService {

    void deleteService(Long id) throws EntityDoesNotExistException;
}
