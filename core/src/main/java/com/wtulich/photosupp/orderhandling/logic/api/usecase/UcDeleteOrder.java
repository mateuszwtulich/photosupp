package com.wtulich.photosupp.orderhandling.logic.api.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.logic.api.exception.EntityHasAssignedEntitiesException;

public interface UcDeleteOrder {

    void deleteOrder(Long id) throws EntityDoesNotExistException, EntityHasAssignedEntitiesException;
}
