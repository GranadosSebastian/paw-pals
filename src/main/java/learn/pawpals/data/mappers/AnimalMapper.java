package learn.pawpals.data.mappers;

import learn.pawpals.models.Animal;
import learn.pawpals.models.Size;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class AnimalMapper implements RowMapper<Animal> {

    // implement RowMapper<animal>

    @Override
    public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
        Animal animal = new Animal();
        animal.setAnimalId(rs.getInt("animal_id"));
        animal.setAnimalName(rs.getString("animal_id"));
        animal.setBreed(rs.getString("breed"));
        animal.setAge(rs.getInt("age"));
        animal.setSize(Size.valueOf(rs.getString("size")));
        animal.setArrivalDate((LocalDate) rs.getObject("arrival_date"));
        animal.setFriendliness(rs.getString("friendliness_level"));
        animal.setAvailable(rs.getBoolean(String.valueOf(rs.getBoolean("is_available"))));
        return animal;
    }
}
