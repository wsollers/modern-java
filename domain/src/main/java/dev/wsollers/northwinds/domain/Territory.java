package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "territories", schema = "northwinds")
public class Territory {

    @Id
    @Column(name = "territory_id", nullable = false, length = 20)
    private String id;

    @Column(name = "territory_description", nullable = false, length = 255)
    private String description; // bpchar

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "territory")
    private Set<EmployeeTerritory> employeeLinks = new HashSet<>();

    public Territory() {}

}
