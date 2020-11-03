package com.wtulich.photosupp.serviceordering.logic.api.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingTo;

import java.util.Optional;

public interface UcManageBooking {

    Optional<BookingEto> createBooking(BookingTo bookingTo) throws EntityAlreadyExistsException, EntityDoesNotExistException;

    Optional<BookingEto> updateBooking(BookingTo bookingTo, Long id) throws EntityAlreadyExistsException, EntityDoesNotExistException;
}
