package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceEto;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcFindService;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Validated
@Named
public class UcFindServiceImpl implements UcFindService {
    @Override
    public Optional<List<ServiceEto>> findAllServices() {
        return Optional.empty();
    }
}
