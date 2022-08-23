package learn.pawpals.data;


import learn.pawpals.data.mappers.AnimalMapper;
import learn.pawpals.models.Animal;
import learn.pawpals.models.Species;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AnimalJdbcTemplateRepository implements AnimalRepository {
    private final JdbcTemplate jdbcTemplate;
<<<<<<< HEAD
    private final String FULLANIMALSQLCOLS = " animal_id, animal_name," +
            "breed, age, size, arrival_date, friendliness_level," +
            "is_available, species, user_id ";
=======
    private final String FULLANIMALSQLCOLS = " animal_id, animal_name, " +
            "breed, age, size, arrival_date, friendliness_level, " +
            "species, is_available, user_id ";
>>>>>>> e4a840e4fbbb4598ed330df40f080017cc705251

    public AnimalJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Animal> findAll() {
        final String sql = "select" + FULLANIMALSQLCOLS + "from animal;";
        return jdbcTemplate.query(sql, new AnimalMapper());
    }

    @Override
    @Transactional
    public List<Animal> findBySpecies(Species species) {

        final String sql = "select" + FULLANIMALSQLCOLS +
                "from animal where species = ?;";


        List<Animal> results = jdbcTemplate.query(sql, new AnimalMapper(), species).stream()
                .collect(Collectors.toList());

        return results;
    }

    @Override
    public Animal add(Animal animal) {

        final String sql = "insert into animal (" + FULLANIMALSQLCOLS + ") " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, animal.getAnimalName());
            ps.setString(2, animal.getBreed());
            ps.setInt(3, animal.getAge());
            ps.setObject(4, animal.getSize());
            ps.setObject(5, animal.getArrivalDate());
            ps.setString(6, animal.getFriendliness());
            ps.setBoolean(7, animal.isAvailable());
            ps.setObject(8, animal.getSpecies());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        animal.setAnimalId(keyHolder.getKey().intValue());
        return animal;
    }

    @Override
    public boolean update(Animal animal) {
        final String sql = "update animal set " +
                "animal_name = ?, " +
                "breed = ?, " +
                "age = ?, " +
                "size = ?, " +
                "arrival_date = ?, " +
                "friendliness_level = ?," +
                "is_available = ?," +
                "species = ?, " +
                "user_id = ? " +
                "where animal_id = ?;";

        return jdbcTemplate.update(sql, animal.getAnimalName(),
                                        animal.getBreed(),
                                        animal.getAge(),
                                        animal.getSize(),
                                        animal.getArrivalDate(),
                                        animal.getFriendliness(),
                                        animal.isAvailable(),
                                        animal.getSpecies(),
                                        animal.getAnimalId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int animalId) {
        jdbcTemplate.update("delete from `schedule` where animal_id = ?", animalId);
        return jdbcTemplate.update("delete from animal where animal_id = ?", animalId) > 0;
    }


}
