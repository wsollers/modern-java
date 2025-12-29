package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailId implements Serializable {

    @Column(name = "order_id", nullable = false)
    private Short orderId;

    @Column(name = "product_id", nullable = false)
    private Short productId;

    public OrderDetailId() {}

    public OrderDetailId(Short orderId, Short productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailId that)) return false;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
    }
    @Override public int hashCode() { return Objects.hash(orderId, productId); }
}
