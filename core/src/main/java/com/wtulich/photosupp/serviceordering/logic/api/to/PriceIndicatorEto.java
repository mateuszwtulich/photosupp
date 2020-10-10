package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class PriceIndicatorEto {

    @NotNull
    private IndicatorEto indicatorEto;

    private BookingEto bookingEto;

    @NotNull
    private Double indicatorPrice;

    @NotNull
    private Integer finalAmount;

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

    public Double getIndicatorPrice() {
        return indicatorPrice;
    }

    public void setIndicatorPrice(Double indicatorPrice) {
        this.indicatorPrice = indicatorPrice;
    }

    public Integer getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Integer finalAmount) {
        this.finalAmount = finalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceIndicatorEto)) return false;
        PriceIndicatorEto that = (PriceIndicatorEto) o;
        return indicatorEto.equals(that.indicatorEto) &&
                Objects.equals(bookingEto, that.bookingEto) &&
                indicatorPrice.equals(that.indicatorPrice) &&
                finalAmount.equals(that.finalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicatorEto, bookingEto, indicatorPrice, finalAmount);
    }
}
