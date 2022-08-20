package learn.pawpals.data.mappers;

import learn.pawpals.models.animal;


public class animalMapper {

    // implement RowMapper<animal>

    public animal mapRow() {
        animal animal = new animal();
        // set variables
        return animal;
    }

}
