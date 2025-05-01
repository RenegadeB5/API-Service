package com.test_deploy.API_Service.Utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.test_deploy.API_Service.Entities.Individual;
import com.test_deploy.API_Service.Controllers.IndividualController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class IndividualModelAssembler implements RepresentationModelAssembler<Individual, EntityModel<Individual>> {

    @Override
    public EntityModel<Individual> toModel(Individual individual) {
        return EntityModel.of(individual,
                linkTo(methodOn(IndividualController.class).one(individual.getId())).withSelfRel(),
                linkTo(methodOn(IndividualController.class).all()).withRel("individuals"));
    }
}
