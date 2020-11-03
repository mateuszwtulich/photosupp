package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.logic.api.exception.EntityHasAssignedEntitiesException;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcDeleteIndicator;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;

@Validated
@Named
public class UcDeleteIndicatorImpl implements UcDeleteIndicator {
    @Override
    public void deleteIndicator(Long id) throws EntityDoesNotExistException, EntityHasAssignedEntitiesException {

    }
}
