package com.wtulich.photosupp.serviceordering.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class AddressEto {

    @NotNull
    private Long id;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String buildingNumber;

    @NotNull
    private String apartmentNumber;

    @NotNull
    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEto)) return false;
        AddressEto that = (AddressEto) o;
        return id.equals(that.id) &&
                city.equals(that.city) &&
                street.equals(that.street) &&
                buildingNumber.equals(that.buildingNumber) &&
                apartmentNumber.equals(that.apartmentNumber) &&
                postalCode.equals(that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, buildingNumber, apartmentNumber, postalCode);
    }
}
