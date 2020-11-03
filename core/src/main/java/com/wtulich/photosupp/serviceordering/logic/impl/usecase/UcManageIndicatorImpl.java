package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.to.IndicatorEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.IndicatorTo;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcManageIndicator;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;
import java.util.Optional;

@Validated
@Named
public class UcManageIndicatorImpl implements UcManageIndicator {
    @Override
    public Optional<IndicatorEto> createIndicator(IndicatorTo indicatorTo) {
        return Optional.empty();
    }

    @Override
    public Optional<IndicatorEto> updateIndicator(IndicatorTo indicatorTo, Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
