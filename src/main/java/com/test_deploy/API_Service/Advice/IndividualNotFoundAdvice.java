package com.test_deploy.API_Service.Advice;

import com.test_deploy.API_Service.Exceptions.IndividualNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IndividualNotFoundAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IndividualNotFoundException.class)
    String individualNotFoundHandler(IndividualNotFoundException e) {
        return e.getMessage();
    }
}