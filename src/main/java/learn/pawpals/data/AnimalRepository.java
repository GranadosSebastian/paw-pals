package learn.pawpals.data;

import learn.pawpals.models.Animal;
import learn.pawpals.models.Species;

import java.util.List;

public interface AnimalRepository {
    // findAll
    //findBySpecies
    //add
    //update
    //delete
    List<Animal> findAll();


    List<Animal> findBySpecies(String speciesString);


    Animal add(Animal animal);

    boolean update(Animal animal);

    boolean deleteById(int animalId);
}
