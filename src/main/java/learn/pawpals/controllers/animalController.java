package learn.pawpals.controllers;

import learn.pawpals.domain.animalService;
import learn.pawpals.models.animal;

import java.util.List;

public class animalController {

    private final animalService service;
    public animalController(animalService service) {
        this.service = service;
    }

    // findAll
    //findBySpecies
    //add
    //update
    //delete

    //@GetMapping
    public void findAll() {
        //ResponseEntity
        //try/catch
        //return new Response entity
    }

    //@GetMapping("/species/{species}")
    public animal findBySpecies() {
        return service.findBySpecies();
    }

    //@PostMapping
    public void add() {
        //result
        //return new ResponseEntity
    }

    //@PutMapping("/{id}")
    public void update() {
        //return new ResponseEntity
    }

    //@DeleteMapping("/{id}")
    public void delete() {
        //result
        //return result
    }


}
