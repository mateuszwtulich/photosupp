package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class PriceIndicatorEto {

    @NotNull
    private IndicatorEto indicatorEto;

    private BookingEto bookingEto;

    @NotNull
    private Integer indicatorPrice;

    public PriceIndicatorEto() {
    }

    public PriceIndicatorEto(IndicatorEto indicatorEto, BookingEto bookingEto, Integer indicatorPrice) {
        this.indicatorEto = indicatorEto;
        this.bookingEto = bookingEto;
        this.indicatorPrice = indicatorPrice;
    }

    public IndicatorEto getIndicatorEto() {
        return indicatorEto;
    }

    public void setIndicatorEto(IndicatorEto indicatorEto) {
        this.indicatorEto = indicatorEto;
    }

    public BookingEto getBookingEto() {
        return bookingEto;
    }

    public void setBookingEto(BookingEto bookingEto) {
        this.bookingEto = bookingEto;
    }

    public Integer getIndicatorPrice() {
        return indicatorPrice;
    }

    public void setIndicatorPrice(Integer indicatorPrice) {
        this.indicatorPrice = indicatorPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceIndicatorEto)) return false;
        PriceIndicatorEto that = (PriceIndicatorEto) o;
        return indicatorEto.equals(that.indicatorEto) &&
                Objects.equals(bookingEto, that.bookingEto) &&
                indicatorPrice.equals(that.indicatorPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicatorEto, bookingEto, indicatorPrice);
    }
}
