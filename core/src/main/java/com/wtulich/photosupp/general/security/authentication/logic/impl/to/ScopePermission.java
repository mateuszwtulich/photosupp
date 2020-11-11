package com.wtulich.photosupp.general.security.authentication.logic.impl.to;

public class ScopePermission {

    private Long userId;

    private boolean isAdmin;

    private String orderNumber;

    private Long bookingId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public boolean setIsAdmin() {
        return isAdmin;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
