package learn.pawpals.data;

import learn.pawpals.models.animals;
import java.util.List;

public interface animalRepository {
    // findAll
    //findBySpecies
    //add
    //update
    //delete
    List<animals> findAll();

    List<animals> findBySpecies();

    animals add();

    boolean update();

    boolean delete();
}
