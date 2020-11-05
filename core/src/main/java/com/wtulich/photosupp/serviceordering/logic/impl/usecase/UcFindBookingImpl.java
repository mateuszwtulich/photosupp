package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.BookingDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.BookingEntity;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.AddressMapper;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.BookingMapper;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.IndicatorMapper;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.ServiceMapper;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.PriceIndicatorEto;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcFindBooking;
import com.wtulich.photosupp.userhandling.logic.api.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Named
public class UcFindBookingImpl implements UcFindBooking {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindBookingImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String GET_BOOKING_LOG = "Get Booking with id {} from database.";
    private static final String GET_ALL_BOOKINGS_LOG = "Get all Bookings from database.";

    @Inject
    private BookingDao bookingDao;

    @Inject
    private BookingMapper bookingMapper;

    @Inject
    private ServiceMapper serviceMapper;

    @Inject
    private AddressMapper addressMapper;

    @Inject
    private UserMapper userMapper;

    @Inject
    private IndicatorMapper indicatorMapper;

    @Override
    public Optional<BookingEto> findBooking(Long id) throws EntityDoesNotExistException {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);
        LOG.debug(GET_BOOKING_LOG, id);

        return Optional.of(toBookingEto(bookingDao.findById(id).orElseThrow(() ->
                new EntityDoesNotExistException("Booking with id " + id + " does not exist."))));
    }

    @Override
    public Optional<List<BookingEto>> findAllBookings() {
        LOG.debug(GET_ALL_BOOKINGS_LOG);

        return Optional.of(bookingDao.findAll().stream()
                .map(bookingEntity -> toBookingEto(bookingEntity))
                .collect(Collectors.toList()));
    }

    private BookingEto toBookingEto(BookingEntity bookingEntity){
        BookingEto bookingEto = bookingMapper.toBookingEto(bookingEntity);

        bookingEto.setServiceEto(serviceMapper.toServiceEto(bookingEntity.getService()));
        bookingEto.setUserEto(userMapper.toUserEto(bookingEntity.getUser()));

        if( bookingEntity.getAddress() != null ){
            bookingEto.setAddressEto(addressMapper.toAddressEto(bookingEntity.getAddress()));
        }

        if( bookingEntity.getPriceIndicatorList() != null ) {
            bookingEto.setPriceIndicatorEtoList(
                    bookingEntity.getPriceIndicatorList().stream()
                            .map(priceIndicatorEntity ->
                                    new PriceIndicatorEto(
                                            indicatorMapper.toIndicatorEto(priceIndicatorEntity.getIndicator()),
                                            bookingEntity.getId(),
                                            priceIndicatorEntity.getIndicatorPrice(),
                                            priceIndicatorEntity.getMultiplier()
                                    )).collect(Collectors.toList()));
        }

        return bookingEto;
    }
}
