package learn.pawpals.domain;

import learn.pawpals.data.animalRepository;
import learn.pawpals.models.animal;
import java.util.List;

public class animalService {

    private final animalRepository animalRepository;


    public animalService(animalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // findAll
    //findBySpecies
    //add
    //update
    //delete

    public List<animal> findAll() {
        return null;
    }

    public animal findBySpecies() {
        return null;
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
