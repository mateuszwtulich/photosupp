package com.wtulich.photosupp.orderhandling.logic.api.to;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationEntityTransportObject;
import com.wtulich.photosupp.general.utils.enums.OrderStatus;
import com.wtulich.photosupp.serviceordering.logic.api.to.BookingEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;

import java.time.LocalDate;
import java.util.Objects;

public class OrderEto extends AbstractApplicationEntityTransportObject {

    @NotNull
    private String orderNumber;

    @NotNull
    private UserEto coordinator;

    @NotNull
    private UserEto user;

    @NotNull
    private OrderStatus status;

    private BookingEto booking;

    @NotNull
    private Double price;

    @NotNull
    private LocalDate createdAt;


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public UserEto getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(UserEto coordinator) {
        this.coordinator = coordinator;
    }

    public UserEto getUser() {
        return user;
    }

    public void setUser(UserEto user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BookingEto getBooking() {
        return booking;
    }

    public void setBooking(BookingEto booking) {
        this.booking = booking;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEto)) return false;
        OrderEto orderEto = (OrderEto) o;
        return  orderNumber.equals(orderEto.orderNumber) &&
                coordinator.equals(orderEto.coordinator) &&
                user.equals(orderEto.user) &&
                status == orderEto.status &&
                Objects.equals(booking, orderEto.booking) &&
                price.equals(orderEto.price) &&
                createdAt.equals(orderEto.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, coordinator, user, status, booking, price, createdAt);
    }
}
