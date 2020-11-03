package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.serviceordering.logic.api.to.IndicatorEto;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcFindIndicator;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Validated
@Named
public class UcFindIndicatorImpl implements UcFindIndicator {
    @Override
    public Optional<List<IndicatorEto>> findAllIndicators() {
        return Optional.empty();
    }
}
