package com.imagine.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.imagine.test.response.ErrorResponse;

@ControllerAdvice
public class EmployeeExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(EmployeeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
