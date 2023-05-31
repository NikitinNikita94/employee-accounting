package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.ecxeptions.EmployeeAlreadyAddedException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeNameException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeNotFoundException;
import com.skypro.employeeaccounting.ecxeptions.EmployeeStorageIsFullException;
import com.skypro.employeeaccounting.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class EmployeeServiceTest {

    private final EmployeeService service = new EmployeeService();

    @BeforeEach
    public void before() {
        service.addEmployee("Вася", 10_000, 1);
        service.addEmployee("Петя", 20_000, 2);
        service.addEmployee("Иван", 30_000, 3);
    }

    @AfterEach
    public void after() {
        service.getAll().clear();
    }

    @Test
    void addEmployee() {
        Employee expected = Employee.builder().name("Олег").salary(25_000).department(1).build();
        assertThat(service.addEmployee("Олег", 25_000, 1))
                .isEqualTo(expected)
                .isIn(service.getAll());

        assertThat(service.getAll()).hasSize(4);
        assertThat(service.findEmployee("Олег", 25_000, 1));
    }

    @Test
    void addAlreadyAddedException() {
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> service.addEmployee("Иван", 30_000, 3));
    }

    @Test
    void addEmployeeIsFullException() {
        Stream.iterate(1, i -> i + 1)
                .limit(4)
                .map(num -> Employee.builder()
                        .name("Виктор" + ((char) ('a' + num)))
                        .salary(num)
                        .department(num)
                        .build()
                )
                .forEach(e -> service.addEmployee(e.getName(), e.getSalary(), e.getDepartment()));


        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> service.addEmployee("Артур", 30_000, 3));
    }

    @Test
    void addEmployeeNameException() {
        assertThatExceptionOfType(EmployeeNameException.class)
                .isThrownBy(() -> service.addEmployee("Артур1", 30_000, 3));
    }

    @Test
    void removeEmployee() {
        Employee expected = Employee.builder().name("Иван").salary(30_000).department(3).build();

        assertThat(service.removeEmployee("Иван", 3))
                .isEqualTo(expected)
                .isNotIn(service.getAll());
        assertThat(service.getAll()).hasSize(2);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> service.findEmployee("Иван", 30_000, 3));
    }

    @Test
    void removeNotFountEmployeeException() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> service.removeEmployee("Артур", 3));
    }

    @Test
    void findEmployee() {
        Employee expected = Employee.builder().name("Иван").salary(30_000).department(3).build();

        assertThat(service.findEmployee("Иван", 30_000, 3))
                .isEqualTo(expected)
                .isIn(service.getAll());
        assertThat(service.getAll()).hasSize(3);
    }

    @Test
    void findEmployeeNotFountEmployeeException() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> service.findEmployee("Артур", 20_000, 3));
    }

    @Test
    void getAllEmployee() {
        assertThat(service.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        Employee.builder().name("Вася").salary(10_000).department(1).build(),
                        Employee.builder().name("Петя").salary(20_000).department(2).build(),
                        Employee.builder().name("Иван").salary(30_000).department(3).build()
                );
    }
}