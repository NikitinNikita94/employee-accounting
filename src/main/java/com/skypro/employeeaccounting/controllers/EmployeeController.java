package com.skypro.employeeaccounting.controllers;

import com.skypro.employeeaccounting.entity.Employee;
import com.skypro.employeeaccounting.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/max-salary")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee getDepartmentMaxSalaryEmployee(@RequestParam("departmentId") Integer departmentId) {
        return service.getDepartmentMaxSalaryEmployee(departmentId);
    }

    @GetMapping("/min-salary")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee getDepartmentMixSalaryEmployee(@RequestParam("departmentId") Integer departmentId) {
        return service.getDepartmentMinSalaryEmployee(departmentId);
    }

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Employee> getAllEmployeeByDep(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        if (departmentId == null) {
            return service.getAllEmployee();
        } else {
            return service.getAllEmployeeByDep(departmentId);
        }
    }
}
