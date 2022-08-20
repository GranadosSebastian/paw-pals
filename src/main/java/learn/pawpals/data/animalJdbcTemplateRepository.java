package learn.pawpals.data;

import learn.pawpals.models.animal;

import java.util.List;

public class animalJdbcTemplateRepository implements animalRepository {
    // findAll
    //findBySpecies
    //add
    //update
    //delete

    @Override
    public List<animal> findAll() {
        return null;
    }

    @Override
    public List<animal> findBySpecies() {
        return null;
    }

    @Override
    public animal add() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

}
