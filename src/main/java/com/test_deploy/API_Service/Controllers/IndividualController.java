package com.test_deploy.API_Service.Controllers;

import java.util.List;
import java.util.stream.Collectors;


import com.test_deploy.API_Service.Entities.Individual;
import com.test_deploy.API_Service.Repositories.IndividualRepository;
import com.test_deploy.API_Service.Exceptions.IndividualNotFoundException;
import com.test_deploy.API_Service.Utils.IndividualModelAssembler;

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

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

@RestController
public class IndividualController {
    private final IndividualRepository individualRepository;

    private final IndividualModelAssembler entityModelAssembler;

    IndividualController(IndividualRepository individualRepository, IndividualModelAssembler assembler) {
        this.individualRepository = individualRepository;
        this.entityModelAssembler = assembler;
    }

    @GetMapping("/individuals")
    public CollectionModel<EntityModel<Individual>> all() {
        List<EntityModel<Individual>> employees = individualRepository.findAll().stream()
            .map(entityModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(IndividualController.class).all()).withSelfRel());
    }

    @GetMapping("/individuals/{id}")

        public EntityModel<Individual> one(@PathVariable Long id) {

            Individual individual = individualRepository.findById(id)
                .orElseThrow(() -> new IndividualNotFoundException(id));
            
            return entityModelAssembler.toModel(individual);

        }

    @PostMapping("/individuals")
    ResponseEntity<?> newIndividual(@RequestBody Individual newIndividual) {
        EntityModel<Individual> entityModel = entityModelAssembler.toModel(individualRepository.save(newIndividual));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping("/individuals/{id}")
    ResponseEntity<?> replaceIndividual(@RequestBody Individual newIndividual, @PathVariable Long id) {

        Individual updatedIndividual = individualRepository.findById(id)
            .map(individual -> {
                individual.setName(newIndividual.getName());
                individual.setEmail(newIndividual.getEmail());
                individual.setPhoneNumber(newIndividual.getPhoneNumber());
                return individualRepository.save(individual);
            })
            .orElseGet(() -> {
                return individualRepository.save(newIndividual);
            });
    

        EntityModel<Individual> entityModel = entityModelAssembler.toModel(updatedIndividual);
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }


    @DeleteMapping("/individuals/{id}")
    ResponseEntity<?> deleteIndividual(@PathVariable Long id) {
        individualRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    

}

