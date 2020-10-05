package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class IndicatorEto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Integer baseAmount;

    @NotNull
    private Integer multiplier;

    @NotNull
    private Double extraPrice;

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

    public Integer getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Integer baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndicatorEto)) return false;
        IndicatorEto that = (IndicatorEto) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                baseAmount.equals(that.baseAmount) &&
                multiplier.equals(that.multiplier) &&
                extraPrice.equals(that.extraPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, baseAmount, multiplier, extraPrice);
    }
}
