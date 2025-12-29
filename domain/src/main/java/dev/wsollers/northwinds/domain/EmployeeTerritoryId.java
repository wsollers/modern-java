package dev.wsollers.northwinds.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EmployeeTerritoryId implements Serializable {

    @Column(name = "employee_id", nullable = false)
    private Short employeeId;

    @Column(name = "territory_id", nullable = false, length = 20)
    private String territoryId;

    public EmployeeTerritoryId() {}

    public EmployeeTerritoryId(Short employeeId, String territoryId) {
        this.employeeId = employeeId;
        this.territoryId = territoryId;
    }

    public Short getEmployeeId() { return employeeId; }
    public void setEmployeeId(Short employeeId) { this.employeeId = employeeId; }

    public String getTerritoryId() { return territoryId; }
    public void setTerritoryId(String territoryId) { this.territoryId = territoryId; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeTerritoryId that)) return false;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(territoryId, that.territoryId);
    }
    @Override public int hashCode() { return Objects.hash(employeeId, territoryId); }
}
