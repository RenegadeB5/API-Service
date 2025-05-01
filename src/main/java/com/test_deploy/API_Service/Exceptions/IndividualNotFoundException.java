package com.test_deploy.API_Service.Exceptions;

public class IndividualNotFoundException extends RuntimeException {
    public IndividualNotFoundException(Long id) {
        super( "Could not find individual with the ID: " + id);
    }
}