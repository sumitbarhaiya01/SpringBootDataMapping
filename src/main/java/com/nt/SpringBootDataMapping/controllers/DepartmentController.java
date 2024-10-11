package com.nt.SpringBootDataMapping.controllers;

import com.nt.SpringBootDataMapping.entities.DepartmentEntity;
import com.nt.SpringBootDataMapping.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId ){
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity)
    {
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping("/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId)
    {
        return  departmentService.assignManagerToDepartment(departmentId,employeeId);
    }

    @PutMapping("{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                    @PathVariable Long employeeId ){
        return departmentService.assignWorkerToDepartment(departmentId, employeeId);
}

    @GetMapping("/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity assignedDepartmentOfManager(@PathVariable Long employeeId){
        return  departmentService.assignedDepartmentOfManager(employeeId);
    }

    @PutMapping("/{departmentId}/freelancers/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(@PathVariable Long departmentId,
                                                         @PathVariable Long employeeId)
    {
        return departmentService.assignFreelancerToDepartment(departmentId,employeeId);
    }
}
