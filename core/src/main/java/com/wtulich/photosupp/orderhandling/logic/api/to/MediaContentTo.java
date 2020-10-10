package com.wtulich.photosupp.orderhandling.logic.api.to;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.utils.enums.MediaType;

import java.util.Objects;

public class MediaContentTo {

    @NotNull
    private MediaType type;

    @NotNull
    private String url;

    @NotNull
    private Long orderId;

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaContentTo)) return false;
        MediaContentTo that = (MediaContentTo) o;
        return type.equals(that.type) &&
                url.equals(that.url) &&
                orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, url, orderId);
    }
}
