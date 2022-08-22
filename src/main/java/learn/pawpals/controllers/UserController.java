package learn.pawpals.controllers;

import learn.pawpals.domain.UserService;
import learn.pawpals.models.User;

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
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
    public User findByRole() {
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
