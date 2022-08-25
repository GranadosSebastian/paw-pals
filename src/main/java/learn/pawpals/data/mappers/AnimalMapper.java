package learn.pawpals.data.mappers;

import learn.pawpals.models.Animal;

import learn.pawpals.models.Size;
import learn.pawpals.models.Species;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class AnimalMapper implements RowMapper<Animal> {

    @Override
    public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
        Animal animal = new Animal();
        animal.setAnimalId(rs.getInt("animal_id"));
        animal.setAnimalName(rs.getString("animal_name"));
        animal.setBreed(rs.getString("breed"));
        animal.setAge(rs.getInt("age"));

        String sizeInString = rs.getString("size");
        Size size = Size.valueOf(sizeInString.toUpperCase());
        animal.setSize(size);

        animal.setArrivalDate(LocalDate.parse(rs.getString("arrival_date")));
        animal.setFriendliness(rs.getString("friendliness_level"));

        if (rs.getInt("is_available") == 0) {
            animal.setAvailable(false);
        } else if (rs.getInt("is_available") == 1) {
            animal.setAvailable(true);
        }

        String speciesInString = rs.getString("species");
        Species species = Species.valueOf(speciesInString);
        animal.setSpecies(species);

        animal.setAppUserId(rs.getInt("app_user_id"));

        return animal;
    }

}

