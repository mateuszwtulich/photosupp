package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class IndicatorTo {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Integer baseAmount;

    @NotNull
    private Integer multiplier;

    @NotNull
    private Double extraPrice;

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
        if (!(o instanceof IndicatorTo)) return false;
        IndicatorTo that = (IndicatorTo) o;
        return name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                baseAmount.equals(that.baseAmount) &&
                multiplier.equals(that.multiplier) &&
                extraPrice.equals(that.extraPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, baseAmount, multiplier, extraPrice);
    }
}
