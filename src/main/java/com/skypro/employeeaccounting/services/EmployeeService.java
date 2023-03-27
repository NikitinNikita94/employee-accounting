package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.ecxeptions.EmployeeAlreadyAddedException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeNotFoundException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeStorageIsFullException;
import com.skypro.employeeaccounting.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private ArrayList<Employee> employees = new ArrayList<>(4);

    public Employee addEmployee(final String firstName, final String lastName) {
        Employee newEmployee = Employee.builder().firstName(firstName).lastName(lastName).build();

        if (employees.size() > 4) {
            throw new EmployeeStorageIsFullException("You have exceeded the size of the array.");
        }

        if (!employees.contains(newEmployee)) {
            employees.add(newEmployee);
            return newEmployee;
        } else {
            throw new EmployeeAlreadyAddedException("An employee with the same first and last name already exists.");
        }
    }

    public Employee removeEmployee(final String firstName, final String lastName) {
        Employee newEmployee = Employee.builder().firstName(firstName).lastName(lastName).build();

        if (!employees.contains(newEmployee)) {
            throw new EmployeeNotFoundException("An employee with that name and surname does not exist.");
        } else {
            employees.remove(newEmployee);
            return newEmployee;
        }
    }

    public Employee findEmployee(final String firstName, final String lastName) {
        Employee newEmployee = Employee.builder().firstName(firstName).lastName(lastName).build();

        if (!employees.contains(newEmployee)) {
            throw new EmployeeNotFoundException("An employee with that name and surname does not exist.");
        } else {
            int index = employees.indexOf(newEmployee);
            return employees.get(index);
        }
    }

    public List<Employee> printEmployee() {
        return employees.isEmpty() ? Collections.emptyList() : employees;
    }
}