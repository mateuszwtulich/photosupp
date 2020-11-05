package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.BookingEntity;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.IndicatorEntity;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.PriceIndicatorEntity;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.ServiceEntity;
import com.wtulich.photosupp.serviceordering.logic.api.to.CalculateCto;
import com.wtulich.photosupp.serviceordering.logic.api.to.CalculateTo;
import com.wtulich.photosupp.serviceordering.logic.api.to.PriceIndicatorTo;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcCalculateService;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@Named
public class UcCalculateServiceImpl implements UcCalculateService {

    @Override
    public Optional<CalculateCto> calculateService(CalculateTo calculateTo) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
