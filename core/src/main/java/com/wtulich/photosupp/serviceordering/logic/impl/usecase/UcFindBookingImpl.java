package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingEto;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcFindBooking;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Validated
@Named
public class UcFindBookingImpl implements UcFindBooking {
    @Override
    public Optional<BookingEto> findBooking(Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }

    @Override
    public Optional<List<BookingEto>> findAllBookings() {
        return Optional.empty();
    }
}
