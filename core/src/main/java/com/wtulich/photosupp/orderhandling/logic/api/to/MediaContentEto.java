package com.wtulich.photosupp.orderhandling.logic.api.to;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationEntityTransportObject;
import com.wtulich.photosupp.general.utils.enums.MediaType;

import java.util.Objects;

public class MediaContentEto extends AbstractApplicationEntityTransportObject {

    @NotNull
    private MediaType type;

    @NotNull
    private String url;

    @NotNull
    private OrderEto orderEto;

    public MediaContentEto() {
    }

    public MediaContentEto(Long id, MediaType type, String url, OrderEto orderEto) {
        super(id);
        this.type = type;
        this.url = url;
        this.orderEto = orderEto;
    }

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

    public OrderEto getOrderEto() {
        return orderEto;
    }

    public void setOrderEto(OrderEto orderEto) {
        this.orderEto = orderEto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaContentEto)) return false;
        if (!super.equals(o)) return false;
        MediaContentEto that = (MediaContentEto) o;
        return type == that.type &&
                url.equals(that.url) &&
                orderEto.equals(that.orderEto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, url, orderEto);
    }
}
