package learn.pawpals.controllers;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.domain.AnimalService;
import learn.pawpals.models.Animal;

import java.util.List;

public class AnimalController {

    private final AnimalService service;
    public AnimalController(AnimalService service) {
        this.service = service;
    }

    // findAll
    //findBySpecies
    //add
    //update
    //delete

    //@GetMapping
    public List<Animal> findAll() throws DataAccessException {
        return service.findAll();
    }

    //@GetMapping("/species/{species}")
    public List<Animal> findBySpecies(int speciesId) throws DataAccessException {
        return service.findBySpecies(speciesId);
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
