package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class ServiceTo {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Double basePrice;

    public ServiceTo() {
    }

    public ServiceTo(String name, String description, Double basePrice) {
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
        if (!(o instanceof ServiceTo)) return false;
        ServiceTo serviceTo = (ServiceTo) o;
        return name.equals(serviceTo.name) &&
                Objects.equals(description, serviceTo.description) &&
                basePrice.equals(serviceTo.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, basePrice);
    }
}
