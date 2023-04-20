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
    public Employee add(@RequestParam("name") String name, @RequestParam("salary") Integer salary, @RequestParam("dept") Integer dept) {
        return service.addEmployee(name, salary, dept);
    }

    @GetMapping("/remove")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee remove(@RequestParam("name") String name, @RequestParam("salary") Integer salary, @RequestParam("dept") Integer dept) {
        return service.removeEmployee(name, salary, dept);
    }

    @GetMapping("/find")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Employee find(@RequestParam("name") String name, @RequestParam("salary") Integer salary, @RequestParam("dept") Integer dept) {
        return service.findEmployee(name, salary, dept);
    }

    @GetMapping("/print_employee")
    public List<Employee> printEmployee() {
        return service.printEmployee();
    }
}
