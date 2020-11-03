package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingTo;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcManageBooking;
import org.springframework.validation.annotation.Validated;

import javax.inject.Named;
import java.util.Optional;

@Validated
@Named
public class UcManageBookingImpl implements UcManageBooking {
    @Override
    public Optional<BookingEto> createBooking(BookingTo bookingTo) throws EntityAlreadyExistsException {
        return Optional.empty();
    }

    @Override
    public Optional<BookingEto> updateBooking(BookingTo bookingTo, Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
