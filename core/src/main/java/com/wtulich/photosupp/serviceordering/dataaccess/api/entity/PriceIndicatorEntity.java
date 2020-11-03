package com.wtulich.photosupp.serviceordering.dataaccess.api.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "PRICE_INDICATOR")
@Entity
public class PriceIndicatorEntity {

    @EmbeddedId
    private PriceIndicatorKey id;

    @ManyToOne(targetEntity = IndicatorEntity.class)
    @MapsId("indicatorId")
    @JoinColumn(name = "INDICATOR_ID", referencedColumnName = "id", insertable = false, updatable = false)
    private IndicatorEntity indicator;

    @ManyToOne(targetEntity = BookingEntity.class)
    @MapsId("bookingId")
    @JoinColumn(name = "BOOKING_ID", referencedColumnName = "id", insertable = false, updatable = false)
    private BookingEntity booking;

    @NotNull
    @Column(name = "INDICATOR_PRICE", nullable = false)
    private Integer indicatorPrice;

    public PriceIndicatorKey getId() {
        return id;
    }

    public void setId(PriceIndicatorKey id) {
        this.id = id;
    }

    public IndicatorEntity getIndicator() {
        return indicator;
    }

    public void setIndicator(IndicatorEntity indicator) {
        this.indicator = indicator;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
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
        if (!(o instanceof PriceIndicatorEntity)) return false;
        PriceIndicatorEntity that = (PriceIndicatorEntity) o;
        return id.equals(that.id) &&
                indicator.equals(that.indicator) &&
                booking.equals(that.booking) &&
                indicatorPrice.equals(that.indicatorPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, indicator, booking, indicatorPrice);
    }
}
