package com.wtulich.photosupp.serviceordering.dataaccess.api.entity;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationPersistenceEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOKING")
public class BookingEntity extends AbstractApplicationPersistenceEntity {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "START_DATE", nullable = false)
    private LocalDate start;

    @NotNull
    @Column(name = "END_DATE", nullable = false)
    private LocalDate end;

    @NotNull
    @Column(name = "FINAL_PRICE", nullable = false)
    private Double finalPrice;

    @NotNull
    @Column(name = "MODIFICATION_DATE", nullable = false)
    private LocalDate modificationDate;

    @NotNull
    @Column(name = "IS_CONFIRMED", nullable = false)
    private boolean isConfirmed = false;

    @NotNull
    @ManyToOne(targetEntity = UserEntity.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @NotNull
    @ManyToOne(targetEntity = ServiceEntity.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "id", nullable = false)
    private ServiceEntity service;

    @NotNull
    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, targetEntity = PriceIndicatorEntity.class)
    private List<PriceIndicatorEntity> priceIndicatorList;

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

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public List<PriceIndicatorEntity> getPriceIndicatorList() {
        return priceIndicatorList;
    }

    public void setPriceIndicatorList(List<PriceIndicatorEntity> priceIndicatorList) {
        this.priceIndicatorList = priceIndicatorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingEntity)) return false;
        BookingEntity that = (BookingEntity) o;
        return isConfirmed == that.isConfirmed &&
                name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                start.equals(that.start) &&
                end.equals(that.end) &&
                finalPrice.equals(that.finalPrice) &&
                modificationDate.equals(that.modificationDate) &&
                user.equals(that.user) &&
                service.equals(that.service) &&
                priceIndicatorList.equals(that.priceIndicatorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, start, end, finalPrice, modificationDate, isConfirmed, user, service, priceIndicatorList);
    }
}
