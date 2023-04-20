package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.ecxeptions.EmployeeAlreadyAddedException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeNotFoundException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeStorageIsFullException;
import com.skypro.employeeaccounting.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private static int CAPACITY = 4;
    static Map<String, Employee> employeeMap = new HashMap<>(CAPACITY);

    public Employee addEmployee(final String name, final int salary, final int department) {
        Employee newEmployee = Employee.builder().name(name).salary(salary).department(department).build();

        if (employeeMap.size() > 4) {
            throw new EmployeeStorageIsFullException("You have exceeded the size of the Map.");
        }

        if (!employeeMap.containsValue(newEmployee)) {
            employeeMap.put(newEmployee.getName(), newEmployee);
            return newEmployee;
        } else {
            throw new EmployeeAlreadyAddedException("An employee with the name already exists.");
        }
    }

    public Employee removeEmployee(final String name, final int salary, final int department) {
        Employee newEmployee = Employee.builder().name(name).salary(salary).department(department).build();

        if (!employeeMap.containsValue(newEmployee)) {
            throw new EmployeeNotFoundException("An employee with that name  does not exist.");
        } else {
            employeeMap.remove(newEmployee.getName());
            return newEmployee;
        }
    }

    public Employee findEmployee(final String name, final int salary, final int department) {
        Employee newEmployee = Employee.builder().name(name).salary(salary).department(department).build();

        if (!employeeMap.containsValue(newEmployee)) {
            throw new EmployeeNotFoundException("An employee with that name does not exist.");
        } else {
            return employeeMap.get(newEmployee.getName());
        }
    }

    public List<Employee> printEmployee() {
        return employeeMap.isEmpty() ? Collections.emptyList() : employeeMap.values().stream().toList();
    }
}
