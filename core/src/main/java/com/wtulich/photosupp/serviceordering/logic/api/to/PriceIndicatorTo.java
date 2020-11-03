package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class PriceIndicatorTo {

    @NotNull
    private Long indicatorId;

    private Long bookingId;

    public PriceIndicatorTo() {
    }

    public PriceIndicatorTo(Long indicatorId, Long bookingId) {
        this.indicatorId = indicatorId;
        this.bookingId = bookingId;
    }

    public Long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceIndicatorTo)) return false;
        PriceIndicatorTo that = (PriceIndicatorTo) o;
        return indicatorId.equals(that.indicatorId) &&
                Objects.equals(bookingId, that.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicatorId, bookingId);
    }
}
