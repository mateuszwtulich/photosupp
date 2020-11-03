package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationEntityTransportObject;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BookingEtoWithOrderId extends AbstractApplicationEntityTransportObject {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private ServiceEto serviceEto;

    private AddressEto addressEto;

    @NotNull
    private boolean isConfirmed;

    private Double predictedPrice;

    @NotNull
    private String start;

    @NotNull
    private String end;

    @NotNull
    private String modificationDate;

    private List<PriceIndicatorEto> priceIndicatorEtoList;

    @NotNull
    private Long orderId;

    public BookingEtoWithOrderId() {
    }

    public BookingEtoWithOrderId(Long id, String name, String description, ServiceEto serviceEto, AddressEto addressEto,
                                 boolean isConfirmed, Double predictedPrice, String start, String end,
                                 String modificationDate, List<PriceIndicatorEto> priceIndicatorEtoList, Long orderId) {
        super(id);
        this.name = name;
        this.description = description;
        this.serviceEto = serviceEto;
        this.addressEto = addressEto;
        this.isConfirmed = isConfirmed;
        this.predictedPrice = predictedPrice;
        this.start = start;
        this.end = end;
        this.modificationDate = modificationDate;
        this.priceIndicatorEtoList = priceIndicatorEtoList;
        this.orderId = orderId;
    }

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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public List<PriceIndicatorEto> getPriceIndicatorEtoList() {
        return priceIndicatorEtoList;
    }

    public void setPriceIndicatorEtoList(List<PriceIndicatorEto> priceIndicatorEtoList) {
        this.priceIndicatorEtoList = priceIndicatorEtoList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingEtoWithOrderId)) return false;
        if (!super.equals(o)) return false;
        BookingEtoWithOrderId that = (BookingEtoWithOrderId) o;
        return isConfirmed == that.isConfirmed &&
                name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                serviceEto.equals(that.serviceEto) &&
                Objects.equals(addressEto, that.addressEto) &&
                Objects.equals(predictedPrice, that.predictedPrice) &&
                start.equals(that.start) &&
                end.equals(that.end) &&
                modificationDate.equals(that.modificationDate) &&
                Objects.equals(priceIndicatorEtoList, that.priceIndicatorEtoList) &&
                orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, serviceEto, addressEto, isConfirmed, predictedPrice, start, end, modificationDate, priceIndicatorEtoList, orderId);
    }
}
