package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.BookingDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.BookingEntity;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcDeleteBooking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;

@Validated
@Named
public class UcDeleteBookingImpl implements UcDeleteBooking {

    private static final Logger LOG = LoggerFactory.getLogger(UcDeleteBookingImpl.class);
    private static final String DELETE_BOOKING_LOG = "Delete Booking with id {} in database.";
    private static final String DELETE_PRICE_INDICATORS_LOG = "Delete Price Indicators on booking with id {} in database.";

    @Inject
    private BookingDao bookingDao;

    @Override
    public void deleteBooking(Long id) throws EntityDoesNotExistException {
        BookingEntity bookingEntity = bookingDao.findById(id).orElseThrow(() ->
                new EntityDoesNotExistException("User with id " + id + " does not exist."));

        LOG.debug(DELETE_BOOKING_LOG, bookingEntity.getId());
        LOG.debug(DELETE_PRICE_INDICATORS_LOG, bookingEntity.getId());

        bookingDao.deleteById(bookingEntity.getId());
    }
}
