package com.test_deploy.API_Service.Controllers;

import java.util.List;

import com.test_deploy.API_Service.Entities.Vehicle;
import com.test_deploy.API_Service.Repositories.VehicleRepository;
import com.test_deploy.API_Service.Exceptions.VehicleNotFoundException;
import com.test_deploy.API_Service.Utils.VehicleModelAssembler;
import com.test_deploy.API_Service.Utils.Usage;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

//import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;


@RestController
public class VehicleController {
    private final VehicleRepository vehicleRepository;
    private final VehicleModelAssembler entityModelAssembler;

    VehicleController(VehicleRepository vehicleRepository, VehicleModelAssembler assembler) {
        this.vehicleRepository = vehicleRepository;
        this.entityModelAssembler = assembler;
    }

    @GetMapping("/vehicles")
    public CollectionModel<EntityModel<Vehicle>> all() {
        List<EntityModel<Vehicle>> vehicles = vehicleRepository.findAll().stream()
            .map(entityModelAssembler::toModel)
            .toList();
        return CollectionModel.of(vehicles, linkTo(methodOn(VehicleController.class).all()).withSelfRel());
    }
    @GetMapping("/vehicles/{VIN}")
    public EntityModel<Vehicle> one(@PathVariable String VIN) {
        Vehicle vehicle = vehicleRepository.findById(VIN)
            .orElseThrow(() -> new VehicleNotFoundException(VIN));
        return entityModelAssembler.toModel(vehicle);
    }

    @PostMapping("/vehicles")
    ResponseEntity<EntityModel<Vehicle>> newVehicle(@RequestBody Vehicle vehicle) {
        vehicle.setUsage(Usage.INVENTORY);
        Vehicle newVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity
            .created(linkTo(methodOn(VehicleController.class).one(newVehicle.getVIN())).toUri())
            .body(entityModelAssembler.toModel(newVehicle));
    }

    // @PutMapping("/vehicles/{VIN}")
    // ResponseEntity<?> replaceVehicle(@RequestBody Vehicle newVehicle, @PathVariable String VIN) {
    //     Vehicle updatedVehicle = vehicleRepository.findById(VIN)
    //         .map(vehicle -> {
    //             vehicle.setUsage(newVehicle.getUsage());
    //             return vehicleRepository.save(vehicle);
    //         })
    //         .orElseGet(() -> {
    //             return vehicleRepository.save(newVehicle);
    //         });
    //     EntityModel<Vehicle> entityModel = entityModelAssembler.toModel(updatedVehicle);
    //     return ResponseEntity
    //         .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
    //         .body(entityModel);
    // }

    @PutMapping("/vehicles/{VIN}/rent")
    public ResponseEntity<?> rent(@PathVariable String VIN) {

        Vehicle vehicle = vehicleRepository.findById(VIN)
            .orElseThrow(() -> new VehicleNotFoundException(VIN));
        vehicle.setUsage(Usage.RENTAL);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        EntityModel<Vehicle> entityModel = entityModelAssembler.toModel(updatedVehicle);
        return ResponseEntity.ok(entityModel);

    }

    @PutMapping("/vehicles/{VIN}/lease")
    public ResponseEntity<?> lease(@PathVariable String VIN) {

        Vehicle vehicle = vehicleRepository.findById(VIN)
            .orElseThrow(() -> new VehicleNotFoundException(VIN));
        vehicle.setUsage(Usage.LEASED);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        EntityModel<Vehicle> entityModel = entityModelAssembler.toModel(updatedVehicle);
        return ResponseEntity.ok(entityModel);

    }

    @PutMapping("/vehicles/{VIN}/own")
    public ResponseEntity<?> own(@PathVariable String VIN) {

        Vehicle vehicle = vehicleRepository.findById(VIN)
            .orElseThrow(() -> new VehicleNotFoundException(VIN));
        vehicle.setUsage(Usage.OWNED);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        EntityModel<Vehicle> entityModel = entityModelAssembler.toModel(updatedVehicle);
        return ResponseEntity.ok(entityModel);

    }

    @DeleteMapping("/vehicles/{VIN}")
    ResponseEntity<?> deleteVehicle(@PathVariable String VIN) {
        vehicleRepository.deleteById(VIN);
        return ResponseEntity.noContent().build();
    }
}