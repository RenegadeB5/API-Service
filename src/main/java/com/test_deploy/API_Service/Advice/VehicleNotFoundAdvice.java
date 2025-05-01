package com.test_deploy.API_Service.Advice;

import com.test_deploy.API_Service.Exceptions.VehicleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VehicleNotFoundAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VehicleNotFoundException.class)
    String individualNotFoundHandler(VehicleNotFoundException e) {
        return e.getMessage();
    }
}