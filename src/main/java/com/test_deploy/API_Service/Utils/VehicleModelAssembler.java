package com.test_deploy.API_Service.Utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.test_deploy.API_Service.Entities.Vehicle;
import com.test_deploy.API_Service.Controllers.VehicleController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VehicleModelAssembler implements RepresentationModelAssembler<Vehicle, EntityModel<Vehicle>> {

    @Override
    public EntityModel<Vehicle> toModel(Vehicle vehicle) {
        EntityModel<Vehicle> vehicleModel = EntityModel.of(vehicle,
                linkTo(methodOn(VehicleController.class).one(vehicle.getVIN())).withSelfRel(),
                linkTo(methodOn(VehicleController.class).all()).withRel("vehicles"));
            
        if (vehicle.getUsage() == Usage.INVENTORY) {
            vehicleModel.add(linkTo(methodOn(VehicleController.class).rent(vehicle.getVIN())).withRel("rental"));
            vehicleModel.add(linkTo(methodOn(VehicleController.class).lease(vehicle.getVIN())).withRel("leased"));
            vehicleModel.add(linkTo(methodOn(VehicleController.class).own(vehicle.getVIN())).withRel("owned"));
        }

        return vehicleModel;
    }
}
