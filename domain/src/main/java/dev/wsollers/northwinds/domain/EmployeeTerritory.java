package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_territories", schema = "northwinds")
public class EmployeeTerritory {

    @EmbeddedId
    private EmployeeTerritoryId id;

    @MapsId("employeeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @MapsId("territoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "territory_id", nullable = false)
    private Territory territory;

    public EmployeeTerritory() {}

    public EmployeeTerritory(Employee employee, Territory territory) {
        this.employee = employee;
        this.territory = territory;
        this.id = new EmployeeTerritoryId(employee.getId(), territory.getId());
    }
}
