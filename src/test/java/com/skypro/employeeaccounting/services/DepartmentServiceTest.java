package com.skypro.employeeaccounting.services;

import com.skypro.employeeaccounting.ecxeptions.DepartmentNotFoundException;
import com.skypro.employeeaccounting.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    public static Stream<Arguments> getDepartmentMaxSalaryParam() {
        return Stream.of(
                Arguments.of(1, 110_000),
                Arguments.of(2, 40_000),
                Arguments.of(3, 30_000)
        );
    }

    public static Stream<Arguments> getDepartmentMinSalaryParam() {
        return Stream.of(
                Arguments.of(1, 10_000),
                Arguments.of(2, 20_000),
                Arguments.of(3, 29_000)
        );
    }

    public static Stream<Arguments> getDepartmentSumBySalaryDepartParam() {
        return Stream.of(
                Arguments.of(1, 120_000),
                Arguments.of(2, 60_000),
                Arguments.of(3, 59_000)
        );
    }

    public static Stream<Arguments> getAllEmployeeByDepParam() {
        return Stream.of(
                Arguments.of(1,
                        List.of(
                                Employee.builder().name("Вася").salary(10_000).department(1).build(),
                                Employee.builder().name("Алексей").salary(110_000).department(1).build()
                        )),
                Arguments.of(2,
                        List.of(Employee.builder().name("Петя").salary(20_000).department(2).build(),
                                Employee.builder().name("Виктор").salary(40_000).department(2).build()
                        )),
                Arguments.of(3,
                        List.of(Employee.builder().name("Иван").salary(30_000).department(3).build(),
                                Employee.builder().name("Коля").salary(29_000).department(3).build()
                        )),
                Arguments.of(4, Collections.emptyList())
        );
    }

    @BeforeEach
    public void before() {
        when(employeeService.getAll()).thenReturn(List.of(
                Employee.builder().name("Вася").salary(10_000).department(1).build(),
                Employee.builder().name("Петя").salary(20_000).department(2).build(),
                Employee.builder().name("Иван").salary(30_000).department(3).build(),
                Employee.builder().name("Коля").salary(29_000).department(3).build(),
                Employee.builder().name("Виктор").salary(40_000).department(2).build(),
                Employee.builder().name("Алексей").salary(110_000).department(1).build()
        ));
    }

    @ParameterizedTest
    @MethodSource("getDepartmentMaxSalaryParam")
    void getDepartmentMaxSalary(int departId, int expected) {
        Assertions.assertThat(departmentService.getDepartmentMaxSalary(departId))
                .isEqualTo(expected);
    }

    @Test
    void getDepartmentMaxSalaryNotFound() {
        assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.getDepartmentMaxSalary(5));
    }

    @ParameterizedTest
    @MethodSource("getDepartmentMinSalaryParam")
    void getDepartmentMinSalary(int departId, int expected) {
        Assertions.assertThat(departmentService.getDepartmentMinSalary(departId))
                .isEqualTo(expected);
    }

    @Test
    void getDepartmentMixSalaryNotFound() {
        assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.getDepartmentMinSalary(5));
    }

    @ParameterizedTest
    @MethodSource("getDepartmentSumBySalaryDepartParam")
    void getDepartmentSumBySalaryDepart(int departId, int expected) {
        Assertions.assertThat(departmentService.getDepartmentSumBySalaryDepart(departId))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getAllEmployeeByDepParam")
    void getAllEmployeeByDep(int departId, List<Employee> expected) {
        Assertions.assertThat(departmentService.getAllEmployeeByDepart(departId))
                .isEqualTo(expected);
    }

    @Test
    void getAllEmployee() {
        Map<Integer, List<Employee>> expected = Map.of(
                1, List.of(
                        Employee.builder().name("Вася").salary(10_000).department(1).build(),
                        Employee.builder().name("Алексей").salary(110_000).department(1).build()
                ),
                2,
                List.of(Employee.builder().name("Петя").salary(20_000).department(2).build(),
                        Employee.builder().name("Виктор").salary(40_000).department(2).build()
                ),
                3,
                List.of(Employee.builder().name("Иван").salary(30_000).department(3).build(),
                        Employee.builder().name("Коля").salary(29_000).department(3).build()
                )
        );

        Assertions.assertThat(departmentService.getAllEmployeeDepart())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }
}