package com.test_deploy.API_Service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test_deploy.API_Service.Entities.Vehicle;



public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}