package learn.pawpals.controllers;

import learn.pawpals.domain.scheduleService;
import learn.pawpals.models.animal;
import learn.pawpals.models.schedule;

public class scheduleController {

    private final scheduleService service;

    public scheduleController(scheduleService service) {
        this.service = service;
    }

    //findAll
    //findByDate
    //findByAnimal
    //findByAdopter
    //add
    //update
    //delete

    //@GetMapping
    public void findAll() {
        //ResponseEntity
        //try/catch
        //return new Response entity
    }

    //@GetMapping("/date/{date}")
    public schedule findByDate() {
        return null;
    }

    //@GetMapping("/animal/{animal}")
    public schedule findByAnimal() {
        return null;
    }

    //@GetMapping("/adopter/{adopter}")
    public schedule findByAdopter() {
        return null;
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
