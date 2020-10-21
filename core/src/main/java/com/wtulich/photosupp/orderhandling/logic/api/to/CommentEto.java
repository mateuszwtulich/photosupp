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
    private OrderEto orderEto;

    @NotNull
    private UserEto userEto;

    @NotNull
    private LocalDate createdAt;

    public CommentEto() {
    }

    public CommentEto(Long id, String content, OrderEto orderEto, UserEto userEto, LocalDate createdAt) {
        super(id);
        this.content = content;
        this.orderEto = orderEto;
        this.userEto = userEto;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OrderEto getOrderEto() {
        return orderEto;
    }

    public void setOrderEto(OrderEto orderEto) {
        this.orderEto = orderEto;
    }

    public UserEto getUserEto() {
        return userEto;
    }

    public void setUserEto(UserEto userEto) {
        this.userEto = userEto;
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
        if (!(o instanceof CommentEto)) return false;
        CommentEto that = (CommentEto) o;
        return  content.equals(that.content) &&
                orderEto.equals(that.orderEto) &&
                userEto.equals(that.userEto) &&
                createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, orderEto, userEto, createdAt);
    }
}
