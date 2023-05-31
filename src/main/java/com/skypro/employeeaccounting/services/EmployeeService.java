package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.ecxeptions.EmployeeAlreadyAddedException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeNameException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeNotFoundException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeStorageIsFullException;
import com.skypro.employeeaccounting.entity.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private static int CAPACITY = 6;
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(final String name, final int salary, final int department) {
        Employee newEmployee = Employee.builder().name(checkEmployeeName(name)).salary(salary).department(department).build();

        if (employees.size() > CAPACITY) {
            throw new EmployeeStorageIsFullException("You have exceeded the size.");
        }

        if (!employees.contains(newEmployee)) {
            employees.add(newEmployee);
            return newEmployee;
        } else {
            throw new EmployeeAlreadyAddedException("An employee with the name already exists.");
        }
    }

    public Employee removeEmployee(final String name, final int department) {
        Employee newEmployee = employees.stream()
                .filter(e -> e.getName().equals(name) && e.getDepartment().equals(department))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("An employee with that name  does not exist."));

        employees.remove(newEmployee);
        return newEmployee;
    }

    public Employee findEmployee(final String name, final int salary, final int department) {
        Employee newEmployee = Employee.builder().name(checkEmployeeName(name)).salary(salary).department(department).build();

        return employees.stream()
                .filter(employee -> employee.equals(newEmployee))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found!"));
    }

    public List<Employee> getAll() {
        return employees.isEmpty() ? Collections.emptyList() : new ArrayList<>(employees);
    }

    private String checkEmployeeName(final String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new EmployeeNameException("Invalid username entered.");
        }
        return StringUtils.capitalize(StringUtils.deleteWhitespace(name.toLowerCase()));
    }
}
