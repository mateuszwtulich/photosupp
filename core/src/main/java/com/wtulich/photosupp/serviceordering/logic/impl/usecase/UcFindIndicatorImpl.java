package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.serviceordering.logic.api.to.IndicatorEto;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcFindIndicator;

import java.util.List;
import java.util.Optional;

public class UcFindIndicatorImpl implements UcFindIndicator {
    @Override
    public Optional<List<IndicatorEto>> findAllIndicators() {
        return Optional.empty();
    }
}
