package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class PriceIndicatorTo {

    @NotNull
    private Long indicatorId;

    private Long bookingId;

    @NotNull
    private Double indicatorPrice;

    @NotNull
    private Integer finalAmount;

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
        if (!(o instanceof PriceIndicatorTo)) return false;
        PriceIndicatorTo that = (PriceIndicatorTo) o;
        return indicatorId.equals(that.indicatorId) &&
                Objects.equals(bookingId, that.bookingId) &&
                indicatorPrice.equals(that.indicatorPrice) &&
                finalAmount.equals(that.finalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicatorId, bookingId, indicatorPrice, finalAmount);
    }
}
