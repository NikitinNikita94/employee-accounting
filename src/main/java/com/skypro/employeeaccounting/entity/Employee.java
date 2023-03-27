package com.skypro.employeeaccounting.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Employee {
    private String firstName;
    private String lastName;
}
