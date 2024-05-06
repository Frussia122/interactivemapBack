package com.example.apiWithDb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleEmployeeNotFoundException
            (EmployeeNotFoundException employeeNotFoundException)
    {
        EmployeeException employeeException = new EmployeeException(
                employeeNotFoundException.getMessage(),
                employeeNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(employeeException,HttpStatus.NOT_FOUND);
    }

}
