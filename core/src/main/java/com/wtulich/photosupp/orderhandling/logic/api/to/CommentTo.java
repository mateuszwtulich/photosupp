package com.wtulich.photosupp.orderhandling.logic.api.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class CommentTo {

    @NotNull
    private String content;

    @NotNull
    private Long orderId;

    @NotNull
    private Long userId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentTo)) return false;
        CommentTo commentTo = (CommentTo) o;
        return content.equals(commentTo.content) &&
                orderId.equals(commentTo.orderId) &&
                userId.equals(commentTo.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, orderId, userId);
    }
}
