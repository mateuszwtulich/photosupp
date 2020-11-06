package com.wtulich.photosupp.orderhandling.dataaccess.api.entity;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationPersistenceEntity;
import com.wtulich.photosupp.general.dataaccess.api.generators.StringPrefixedSequenceIdGenerator;
import com.wtulich.photosupp.general.utils.enums.OrderStatus;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.BookingEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PHOTOSUPP_ORDER")
public class OrderEntity extends AbstractApplicationPersistenceEntity {
    private static final long serialVersionUID = 1L;

    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_number_seq")
    @GenericGenerator(
            name = "order_number_seq",
            strategy = "com.wtulich.photosupp.general.dataaccess.api.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "INVIU_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @Column(name = "ORDER_NUMBER", nullable = false, unique = true)
    private Long orderNumber;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "id")
    private UserEntity user;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "COORDINATOR_ID", nullable = false, referencedColumnName = "id")
    private UserEntity coordinator;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID", nullable = true, referencedColumnName = "id", unique = true)
    private BookingEntity booking;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, targetEntity = CommentEntity.class, orphanRemoval = true)
    private List<CommentEntity> commentList;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, targetEntity = MediaContentEntity.class, orphanRemoval = true)
    private List<MediaContentEntity> mediaContentList;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(UserEntity coordinator) {
        this.coordinator = coordinator;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public List<MediaContentEntity> getMediaContentList() {
        return mediaContentList;
    }

    public void setMediaContentList(List<MediaContentEntity> mediaContentList) {
        this.mediaContentList = mediaContentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        OrderEntity that = (OrderEntity) o;
        return orderNumber.equals(that.orderNumber) &&
                status == that.status &&
                price.equals(that.price) &&
                createdAt.equals(that.createdAt) &&
                user.equals(that.user) &&
                coordinator.equals(that.coordinator) &&
                Objects.equals(booking, that.booking) &&
                Objects.equals(mediaContentList, that.mediaContentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, status, price, createdAt, user, coordinator, booking, mediaContentList);
    }
}
