package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_customer_demo", schema = "northwinds")
public class CustomerCustomerDemo {

    @EmbeddedId
    private CustomerCustomerDemoId id;

    @MapsId("customerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @MapsId("customerTypeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_type_id", nullable = false)
    private CustomerDemographic demographic;

    public CustomerCustomerDemo() {}

    public CustomerCustomerDemo(Customer customer, CustomerDemographic demographic) {
        this.customer = customer;
        this.demographic = demographic;
        this.id = new CustomerCustomerDemoId(customer.getId(), demographic.getId());
    }
}
