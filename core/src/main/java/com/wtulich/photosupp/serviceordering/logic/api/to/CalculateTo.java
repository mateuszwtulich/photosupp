package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CalculateTo {

    @NotNull
    private Long serviceId;

    @NotNull
    private Long addressId;

    @NotNull
    private LocalDate start;

    @NotNull
    private LocalDate end;

    @NotNull
    private List<PriceIndicatorTo> priceIndicatorToList;

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
        if (!(o instanceof CalculateTo)) return false;
        CalculateTo that = (CalculateTo) o;
        return serviceId.equals(that.serviceId) &&
                addressId.equals(that.addressId) &&
                start.equals(that.start) &&
                end.equals(that.end) &&
                priceIndicatorToList.equals(that.priceIndicatorToList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, addressId, start, end, priceIndicatorToList);
    }
}
