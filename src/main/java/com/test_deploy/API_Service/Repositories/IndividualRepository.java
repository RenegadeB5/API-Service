package com.test_deploy.API_Service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test_deploy.API_Service.Entities.Individual;



public interface IndividualRepository extends JpaRepository<Individual, Long> {

}