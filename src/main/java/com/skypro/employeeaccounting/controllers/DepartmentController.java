package com.skypro.employeeaccounting.controllers;

import com.skypro.employeeaccounting.entity.Employee;
import com.skypro.employeeaccounting.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping("/{id}/salary/max")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Integer getDepartmentMaxSalaryDepart(@PathVariable int id) {
        return service.getDepartmentMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Integer getDepartmentMinSalaryDepart(@PathVariable int id) {
        return service.getDepartmentMinSalary(id);
    }

    @GetMapping("/{id}/salary/sum")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Integer getDepartmentSumBySalaryDepart(@PathVariable int id) {
        return service.getDepartmentSumBySalaryDepart(id);
    }

    @GetMapping("/{id}/employees")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Employee> getAllEmployeeByDepart(@RequestParam(value = "departmentId", required = false) Integer departmentId, @PathVariable String id) {
        return service.getAllEmployeeByDepart(departmentId);
    }

    @GetMapping("/{id}/employees")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Map<Integer, List<Employee>> getAllEmployeeByDepart() {
        return service.getAllEmployeeDepart();
    }
}
