package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.ecxeptions.DepartmentNotFoundException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeNotFoundException;
import com.skypro.employeeaccounting.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Integer getDepartmentMaxSalary(final int depId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == depId)
                .map(Employee::getSalary)
                .max(Integer::compareTo)
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Integer getDepartmentMinSalary(final int depId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == depId)
                .map(Employee::getSalary)
                .min(Integer::compareTo)
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Integer getDepartmentSumBySalaryDepart(final int depId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == depId)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public List<Employee> getAllEmployeeByDepart(final int depId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == depId)
                .toList();
    }

    public Map<Integer, List<Employee>> getAllEmployeeDepart() {
        return employeeService.getAll().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}