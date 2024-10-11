package com.nt.SpringBootDataMapping.services;

import com.nt.SpringBootDataMapping.entities.DepartmentEntity;
import com.nt.SpringBootDataMapping.entities.EmployeeEntity;
import com.nt.SpringBootDataMapping.repositories.DepartmentRepository;
import com.nt.SpringBootDataMapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity getDepartmentById(Long departmentId) {
        return  departmentRepository.findById(departmentId).orElse(null);
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
     return  departmentRepository.save(departmentEntity);
    }


    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId){
       Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
       Optional<EmployeeEntity>  employeeEntity =employeeRepository.findById(employeeId);

       return departmentEntity.flatMap(department ->
               employeeEntity.map(employee ->{
                   department.setManager(employee);
                   return departmentRepository.save(department);
               })).orElse(null);


}

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity= departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
            employeeEntity.map(employee ->{
                employee.setWorkerDepartment(department);
                employeeRepository.save(employee);

                department.getWorkers().add(employee);
                return department;
            })).orElse(null);
    }

    public DepartmentEntity assignedDepartmentOfManager(Long employeeId) {
    Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
    return employeeEntity.map(employee ->employee.getManagedDepartment()).orElse(null);

    }


    public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {
    Optional<DepartmentEntity> departmentEntity =departmentRepository.findById(departmentId);
    Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

    return  departmentEntity.flatMap(departmemt ->
            employeeEntity.map(employee ->{

                employee.getFreelanceDepartments().add(departmemt);
                employeeRepository.save(employee);

                departmemt.getFreelancers().add(employee);
                departmentRepository.save(departmemt);
                return departmemt;
            })).orElse(null);
    }
}
