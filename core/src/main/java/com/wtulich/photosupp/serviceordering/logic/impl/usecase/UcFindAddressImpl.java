package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcFindAddress;

import java.util.List;
import java.util.Optional;

public class UcFindAddressImpl implements UcFindAddress {
    @Override
    public Optional<List<String>> findAllCities() {
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> findAllStreets() {
        return Optional.empty();
    }
}