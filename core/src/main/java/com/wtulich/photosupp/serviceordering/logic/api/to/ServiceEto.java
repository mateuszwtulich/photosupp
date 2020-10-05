package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class ServiceEto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Double basePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return id.equals(that.id) &&
                name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                basePrice.equals(that.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, basePrice);
    }
}
