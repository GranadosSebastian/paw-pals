package learn.pawpals.controllers;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.domain.AnimalService;

import learn.pawpals.domain.Result;
import learn.pawpals.domain.ResultType;
import learn.pawpals.models.Animal;
import learn.pawpals.models.Species;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    private final AnimalService service;
    public AnimalController(AnimalService service) {
        this.service = service;
    }


    @GetMapping
    public List<Animal> findAll() throws DataAccessException {
        return service.findAll();
    }


    @GetMapping("/{species}")
    public List<Animal> findBySpecies(@PathVariable Species species) throws DataAccessException {
        return service.findBySpecies(species);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Animal animal) throws DataAccessException {
        Result<Animal> result = service.add(animal);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<?> update(@PathVariable int animalId, @RequestBody Animal animal) throws DataAccessException {
        if (animalId != animal.getAnimalId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Animal> result = service.update(animal);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Void> delete(@PathVariable int animalId) throws DataAccessException {
        Result<Animal> result = service.delete((animalId));
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
