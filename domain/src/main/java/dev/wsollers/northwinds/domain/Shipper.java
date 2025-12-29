package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shippers", schema = "northwinds")
public class Shipper {

    @Id
    @Column(name = "shipper_id", nullable = false)
    private Short id;

    @Column(name = "company_name", nullable = false, length = 40)
    private String companyName;

    @Column(name = "phone", length = 24)
    private String phone;

    public Shipper() {}
}
