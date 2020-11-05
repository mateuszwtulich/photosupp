package com.wtulich.photosupp.serviceordering.dataaccess.api.entity;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationPersistenceEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "SERVICE")
public class ServiceEntity extends AbstractApplicationPersistenceEntity {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "BASE_PRICE", nullable = false)
    private Double basePrice;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, targetEntity = BookingEntity.class, orphanRemoval = true)
    private List<BookingEntity> bookingList;

    public ServiceEntity() {
    }

    public ServiceEntity(String name, String description, Double basePrice) {
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

    public List<BookingEntity> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingEntity> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceEntity)) return false;
        ServiceEntity that = (ServiceEntity) o;
        return name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                basePrice.equals(that.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, basePrice);
    }
}
