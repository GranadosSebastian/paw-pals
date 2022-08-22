package learn.pawpals.controllers;

import learn.pawpals.domain.ScheduleService;
import learn.pawpals.models.Schedule;

public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
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
    public Schedule findByDate() {
        return null;
    }

    //@GetMapping("/animal/{animal}")
    public Schedule findByAnimal() {
        return null;
    }

    //@GetMapping("/adopter/{adopter}")
    public Schedule findByAdopter() {
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
