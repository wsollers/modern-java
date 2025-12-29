package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "region", schema = "northwinds")
public class Region {

    @Id
    @Column(name = "region_id", nullable = false)
    private Short id;

    @Column(name = "region_description", nullable = false, length = 255)
    private String description; // bpchar

    @OneToMany(mappedBy = "region")
    private Set<Territory> territories = new HashSet<>();

    public Region() {}
}
