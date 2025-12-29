package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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


}
