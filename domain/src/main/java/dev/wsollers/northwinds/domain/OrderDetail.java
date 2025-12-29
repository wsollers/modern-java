package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_details", schema = "northwinds")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "quantity", nullable = false)
    private Short quantity;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    public OrderDetail() {}

    public OrderDetail(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderDetailId(order.getId(), product.getId());
    }
}
