package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer_demographics", schema = "northwinds")
public class CustomerDemographic {

    @Id
    @Column(name = "customer_type_id", nullable = false, length = 255)
    private String id; // bpchar in your DDL (fixed char)

    @Lob
    @Column(name = "customer_desc")
    private String description;

    @OneToMany(mappedBy = "demographic")
    private Set<CustomerCustomerDemo> customerLinks = new HashSet<>();

    public CustomerDemographic() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<CustomerCustomerDemo> getCustomerLinks() { return customerLinks; }
    public void setCustomerLinks(Set<CustomerCustomerDemo> customerLinks) { this.customerLinks = customerLinks; }
}
