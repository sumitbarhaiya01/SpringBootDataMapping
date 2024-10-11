package com.nt.SpringBootDataMapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="employee01")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long employeeId;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy ="manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="workers_department_id")
    @JsonIgnore
    private  DepartmentEntity workerDepartment;

    @ManyToMany
    @JoinTable(name="freelancer_departments_mapping",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name="department_id")
    )
    @JsonIgnore
    private Set<DepartmentEntity> freelanceDepartments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(name, that.name) && Objects.equals(managedDepartment, that.managedDepartment) && Objects.equals(workerDepartment, that.workerDepartment) && Objects.equals(freelanceDepartments, that.freelanceDepartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, managedDepartment, workerDepartment, freelanceDepartments);
    }
}
