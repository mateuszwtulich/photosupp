package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.to.CalculateCto;
import com.wtulich.photosupp.serviceordering.logic.api.to.CalculateTo;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcCalculateService;

import java.util.Optional;

public class UcCalculateServiceImpl implements UcCalculateService {

    @Override
    public Optional<CalculateCto> calculateService(CalculateTo calculateTo) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
