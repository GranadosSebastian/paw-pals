package learn.pawpals.data;

import learn.pawpals.models.animals;

import java.util.List;

public class animalJdbcTemplateRepository implements animalRepository {
    // findAll
    //findBySpecies
    //add
    //update
    //delete

    @Override
    public List<animals> findAll() {
        return null;
    }

    @Override
    public List<animals> findBySpecies() {
        return null;
    }

    @Override
    public animals add() {
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
