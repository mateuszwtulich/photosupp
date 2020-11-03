package com.wtulich.photosupp.serviceordering.logic.api.mapper;

import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.BookingEntity;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingEto toBookingEto(BookingEntity bookingEntity);

    BookingEntity toBookingEntity(BookingTo bookingTo);
}
