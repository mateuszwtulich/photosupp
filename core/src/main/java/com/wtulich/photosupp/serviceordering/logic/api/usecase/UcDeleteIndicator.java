package com.wtulich.photosupp.serviceordering.logic.api.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;

public interface UcDeleteIndicator {

    void deleteIndicator(Long id) throws EntityDoesNotExistException;
}
