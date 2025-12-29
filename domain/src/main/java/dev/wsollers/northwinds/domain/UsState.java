package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "us_states", schema = "northwinds")
public class UsState {

    @Id
    @Column(name = "state_id", nullable = false)
    private Short id;

    @Column(name = "state_name", length = 100)
    private String stateName;

    @Column(name = "state_abbr", length = 2)
    private String stateAbbr;

    @Column(name = "state_region", length = 50)
    private String stateRegion;

    public UsState() {}
}
