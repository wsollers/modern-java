package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CustomerCustomerDemoId implements Serializable {

    @Column(name = "customer_id", nullable = false, length = 255)
    private String customerId;

    @Column(name = "customer_type_id", nullable = false, length = 255)
    private String customerTypeId;

    public CustomerCustomerDemoId() {}

    public CustomerCustomerDemoId(String customerId, String customerTypeId) {
        this.customerId = customerId;
        this.customerTypeId = customerTypeId;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerCustomerDemoId that)) return false;
        return Objects.equals(customerId, that.customerId) && Objects.equals(customerTypeId, that.customerTypeId);
    }
    @Override public int hashCode() { return Objects.hash(customerId, customerTypeId); }
}
