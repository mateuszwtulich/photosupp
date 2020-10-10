package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BookingTo {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Long serviceId;

    private Long addressId;

    @NotNull
    private boolean isConfirmed;

    private Double predictedPrice;

    @NotNull
    private LocalDate start;

    @NotNull
    private LocalDate end;

    private List<PriceIndicatorTo> priceIndicatorToList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Double getPredictedPrice() {
        return predictedPrice;
    }

    public void setPredictedPrice(Double predictedPrice) {
        this.predictedPrice = predictedPrice;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<PriceIndicatorTo> getPriceIndicatorToList() {
        return priceIndicatorToList;
    }

    public void setPriceIndicatorToList(List<PriceIndicatorTo> priceIndicatorToList) {
        this.priceIndicatorToList = priceIndicatorToList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingTo)) return false;
        BookingTo bookingTo = (BookingTo) o;
        return isConfirmed == bookingTo.isConfirmed &&
                name.equals(bookingTo.name) &&
                Objects.equals(description, bookingTo.description) &&
                serviceId.equals(bookingTo.serviceId) &&
                Objects.equals(addressId, bookingTo.addressId) &&
                Objects.equals(predictedPrice, bookingTo.predictedPrice) &&
                start.equals(bookingTo.start) &&
                end.equals(bookingTo.end) &&
                Objects.equals(priceIndicatorToList, bookingTo.priceIndicatorToList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, serviceId, addressId, isConfirmed, predictedPrice, start, end, priceIndicatorToList);
    }
}
