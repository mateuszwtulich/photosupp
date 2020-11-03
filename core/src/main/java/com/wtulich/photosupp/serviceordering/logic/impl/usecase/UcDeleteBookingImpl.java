package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcDeleteBooking;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;

@Validated
@Named
public class UcDeleteBookingImpl implements UcDeleteBooking {
    @Override
    public void deleteBooking(Long id) throws EntityDoesNotExistException {

    }
}
