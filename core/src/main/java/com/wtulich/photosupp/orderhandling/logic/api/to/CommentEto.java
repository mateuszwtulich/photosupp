package com.wtulich.photosupp.orderhandling.logic.api.to;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationEntityTransportObject;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;

import java.time.LocalDate;
import java.util.Objects;

public class CommentEto extends AbstractApplicationEntityTransportObject {

    @NotNull
    private String content;

    @NotNull
    private Long orderId;

    @NotNull
    private UserEto userEto;

    @NotNull
    private String createdAt;

    public CommentEto() {
    }

    public CommentEto(Long id, String content, Long orderId, UserEto userEto, String createdAt) {
        super(id);
        this.content = content;
        this.orderId = orderId;
        this.userEto = userEto;
        this.createdAt = createdAt;
    }

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

    public UserEto getUserEto() {
        return userEto;
    }

    public void setUserEto(UserEto userEto) {
        this.userEto = userEto;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CommentEto that = (CommentEto) o;
        return content.equals(that.content) &&
                orderId.equals(that.orderId) &&
                userEto.equals(that.userEto) &&
                createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content, orderId, userEto, createdAt);
    }
}
