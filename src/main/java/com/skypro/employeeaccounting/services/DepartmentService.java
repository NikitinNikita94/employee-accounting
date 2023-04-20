package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.skypro.employeeaccounting.services.EmployeeService.employeeMap;

@Service
public class DepartmentService {

    public Employee getDepartmentMaxSalaryEmployee(final Integer depId) {
        return employeeMap.values().stream()
                .filter(employee -> employee.getDepartment().equals(depId))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("No such deportation number exists"));
    }

    public Employee getDepartmentMinSalaryEmployee(final Integer depId) {
        return employeeMap.values().stream()
                .filter(employee -> employee.getDepartment().equals(depId))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("No such deportation number exists"));
    }

    public List<Employee> getAllEmployeeByDep(final Integer depId) {
        return employeeMap.values().stream()
                .filter(employee -> employee.getDepartment().equals(depId))
                .toList();
    }

    public List<Employee> getAllEmployee() {
        return employeeMap.values().stream().toList();
    }
}