package com.skypro.employeeaccounting.controllers;

import com.skypro.employeeaccounting.entity.Employee;
import com.skypro.employeeaccounting.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/add")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return service.addEmployee(firstName,lastName);
    }

    @GetMapping("/remove")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return service.removeEmployee(firstName,lastName);
    }

    @GetMapping("/find")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return service.findEmployee(firstName,lastName);
    }

    @GetMapping("/print_employee")
    public List<Employee> printEmployee() {
        return service.printEmployee();
    }
}
