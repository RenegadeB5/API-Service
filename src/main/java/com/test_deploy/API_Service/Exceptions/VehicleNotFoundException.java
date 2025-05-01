package com.test_deploy.API_Service.Exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String VIN) {
        super( "Could not find vehicle with the ID: " + VIN);
    }
}