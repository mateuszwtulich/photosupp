package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CalculateCto {

    @NotNull
    private ServiceEto serviceEto;

    @NotNull
    private AddressEto addressEto;

    @NotNull
    private Double predictedPrice;

    @NotNull
    private LocalDate start;

    @NotNull
    private LocalDate end;

    @NotNull
    private List<PriceIndicatorEto> priceIndicatorEtoList;

    public ServiceEto getServiceEto() {
        return serviceEto;
    }

    public void setServiceEto(ServiceEto serviceEto) {
        this.serviceEto = serviceEto;
    }

    public AddressEto getAddressEto() {
        return addressEto;
    }

    public void setAddressEto(AddressEto addressEto) {
        this.addressEto = addressEto;
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

    public List<PriceIndicatorEto> getPriceIndicatorEtoList() {
        return priceIndicatorEtoList;
    }

    public void setPriceIndicatorEtoList(List<PriceIndicatorEto> priceIndicatorEtoList) {
        this.priceIndicatorEtoList = priceIndicatorEtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculateCto)) return false;
        CalculateCto that = (CalculateCto) o;
        return serviceEto.equals(that.serviceEto) &&
                addressEto.equals(that.addressEto) &&
                predictedPrice.equals(that.predictedPrice) &&
                start.equals(that.start) &&
                end.equals(that.end) &&
                priceIndicatorEtoList.equals(that.priceIndicatorEtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceEto, addressEto, predictedPrice, start, end, priceIndicatorEtoList);
    }
}
