package com.skypro.employeeaccounting.ecxeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeNameException extends RuntimeException{

    public EmployeeNameException(String message) {
        super(message);
    }
}
