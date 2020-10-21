package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationEntityTransportObject;

import java.util.Objects;

public class ServiceEto extends AbstractApplicationEntityTransportObject {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Double basePrice;

    public ServiceEto() {
    }

    public ServiceEto(Long id, String name, String description, Double basePrice) {
        super(id);
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
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

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceEto)) return false;
        ServiceEto that = (ServiceEto) o;
        return  name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                basePrice.equals(that.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, basePrice);
    }
}
