package learn.pawpals.data;

import learn.pawpals.models.animal;
import java.util.List;

public interface animalRepository {
    // findAll
    //findBySpecies
    //add
    //update
    //delete
    List<animal> findAll();

    List<animal> findBySpecies();

    animal add();

    boolean update();

    boolean delete();
}
