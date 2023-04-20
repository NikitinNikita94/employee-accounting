package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    Map<String, Employee> employeeMap = new HashMap<>();

    {
        Employee firstEmploy = Employee.builder().name("Mad Max").salary(20000).department(5).build();
        Employee secondEmploy = Employee.builder().name("Din Jarin").salary(25000).department(5).build();
        Employee threeEmploy = Employee.builder().name("Joni Sins").salary(30000).department(5).build();
        Employee fourEmploy = Employee.builder().name("EMINEM").salary(60000).department(3).build();
        employeeMap.put(firstEmploy.getName(), firstEmploy);
        employeeMap.put(secondEmploy.getName(), secondEmploy);
        employeeMap.put(threeEmploy.getName(), threeEmploy);
        employeeMap.put(fourEmploy.getName(), fourEmploy);
    }


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