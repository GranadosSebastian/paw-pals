package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.AnimalRepository;
import learn.pawpals.models.Animal;


import java.util.List;


public class AnimalService {

    private final AnimalRepository animalRepository;


    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // findAll
    //findBySpecies
    //add
    //update
    //delete

    public List<Animal> findAll() throws DataAccessException {
        return animalRepository.findAll();
    }

    public List<Animal> findBySpecies(int speciesId) throws DataAccessException {
        return animalRepository.findBySpecies(speciesId);
    }

    public void add() {
        //result
        //return result
    }

    public void update() {
        //result
        //return result
    }

    public void delete() {
        //result
        //return result
    }

    private void validate() {
        //result
        //if conditions & validations
        //return result
    }


}
