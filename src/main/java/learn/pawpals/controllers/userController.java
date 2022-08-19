package learn.pawpals.controllers;

import learn.pawpals.domain.userService;
import learn.pawpals.models.animal;
import learn.pawpals.models.user;

public class userController {

    private final userService service;

    public userController(userService service) {
        this.service = service;
    }

    //findAll
    //findByRole
    //add
    //update
    //delete

    //@GetMapping
    public void findAll() {
        //ResponseEntity
        //try/catch
        //return new Response entity
    }

    //@GetMapping("/roleId/{roleId}")
    public user findByRole() {
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
