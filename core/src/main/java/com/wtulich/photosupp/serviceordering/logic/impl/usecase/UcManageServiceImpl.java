package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceTo;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcManageService;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;
import java.util.Optional;

@Validated
@Named
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
