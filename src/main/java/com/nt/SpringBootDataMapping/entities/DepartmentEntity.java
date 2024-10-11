package com.nt.SpringBootDataMapping.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="department01")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name="department_manager")
    private EmployeeEntity manager;

    @OneToMany(mappedBy = "workerDepartment" ,fetch =FetchType.LAZY)
    private Set<EmployeeEntity> workers;

    @ManyToMany(mappedBy = "freelanceDepartments")
    private Set<EmployeeEntity> freelancers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(title, that.title) && Objects.equals(manager, that.manager) && Objects.equals(workers, that.workers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, title, manager, workers);
    }
}
