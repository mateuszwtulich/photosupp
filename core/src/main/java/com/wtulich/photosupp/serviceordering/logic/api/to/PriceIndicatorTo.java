package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class PriceIndicatorTo {

    @NotNull
    private Long indicatorId;

    private Long bookingId;

    @NotNull
    private Integer multiplier;

    public PriceIndicatorTo() {
    }

    public PriceIndicatorTo(Long indicatorId, Long bookingId, Integer multiplier) {
        this.indicatorId = indicatorId;
        this.bookingId = bookingId;
        this.multiplier = multiplier;
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

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceIndicatorTo that = (PriceIndicatorTo) o;
        return indicatorId.equals(that.indicatorId) &&
                bookingId.equals(that.bookingId) &&
                multiplier.equals(that.multiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicatorId, bookingId, multiplier);
    }
}
