package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceTo;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcManageService;

import java.util.Optional;

public class UcManageServiceImpl implements UcManageService {
    @Override
    public Optional<ServiceEto> createService(ServiceTo serviceTo) {
        return Optional.empty();
    }

    @Override
    public Optional<ServiceEto> updateService(ServiceTo serviceTo, Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
