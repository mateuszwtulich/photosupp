package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class PriceIndicatorEto {

    @NotNull
    private IndicatorEto indicatorEto;

    private Long bookingId;

    @NotNull
    private Integer indicatorPrice;

    @NotNull
    private Integer multiplier;

    public PriceIndicatorEto() {
    }

    public PriceIndicatorEto(IndicatorEto indicatorEto, Long bookingId, Integer indicatorPrice, Integer multiplier) {
        this.indicatorEto = indicatorEto;
        this.bookingId = bookingId;
        this.indicatorPrice = indicatorPrice;
        this.multiplier = multiplier;
    }

    public IndicatorEto getIndicatorEto() {
        return indicatorEto;
    }

    public void setIndicatorEto(IndicatorEto indicatorEto) {
        this.indicatorEto = indicatorEto;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getIndicatorPrice() {
        return indicatorPrice;
    }

    public void setIndicatorPrice(Integer indicatorPrice) {
        this.indicatorPrice = indicatorPrice;
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
        PriceIndicatorEto that = (PriceIndicatorEto) o;
        return indicatorEto.equals(that.indicatorEto) &&
                Objects.equals(bookingId, that.bookingId) &&
                indicatorPrice.equals(that.indicatorPrice) &&
                multiplier.equals(that.multiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicatorEto, bookingId, indicatorPrice, multiplier);
    }
}
