package learn.pawpals.data;

import learn.pawpals.models.Animal;
import java.util.List;

public interface AnimalRepository {
    // findAll
    //findBySpecies
    //add
    //update
    //delete
    List<Animal> findAll();

    List<Animal> findBySpecies(int speciesId);

    Animal add();

    boolean update();

    boolean delete();
}
