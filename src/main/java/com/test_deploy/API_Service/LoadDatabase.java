package com.test_deploy.API_Service;

import com.test_deploy.API_Service.Entities.*;
import com.test_deploy.API_Service.Repositories.*;
import com.test_deploy.API_Service.Utils.Usage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(IndividualRepository individualRepository, VehicleRepository vehicleRepository) {
        return args -> {
            individualRepository.save(new Individual("Kevin Wilson", "kevin_wilson@gmail.com", "1234567890"));
            individualRepository.save(new Individual("John Nelson", "john_nelson1@gmail.com", "7375929592"));
            vehicleRepository.save(new Vehicle("1JTCJ26N5GT103155", "Jeep", "J-10", "1986", Usage.OWNED));
            vehicleRepository.save(new Vehicle("1C3XG7430M0018399", "Chrysler", "Daytona", "1991", Usage.LEASED));
            vehicleRepository.save(new Vehicle("KNDJX3AE7G7005399", "Kia", "Soul", "2016", Usage.INVENTORY));

            individualRepository.findAll().forEach(individual -> {
                log.info("Preloaded " + individual);
            });
            vehicleRepository.findAll().forEach(vehicle -> {
                log.info("Preloaded " + vehicle);
            });
        };
    }
}
// This code is a Spring Boot configuration class that preloads a database with two user entries.