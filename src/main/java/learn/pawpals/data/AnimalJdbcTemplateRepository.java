package learn.pawpals.data;

import learn.pawpals.data.mappers.AnimalMapper;
import learn.pawpals.models.Animal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AnimalJdbcTemplateRepository implements AnimalRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String FULLANIMALSQLCOLS = " animal_id, animal_name," +
            "breed, age, size, arrival_date, friendliness_level," +
            "is_available ";

    public AnimalJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Animal> findAll() {
        final String sql = "select" + FULLANIMALSQLCOLS + "from animal;";
        return jdbcTemplate.query(sql, new AnimalMapper());
    }

    @Override
    public List<Animal> findBySpecies(int speciesId) {

        final String sql = "select" + FULLANIMALSQLCOLS +
                "from animal where species_id = ?;";


        List<Animal> results = jdbcTemplate.query(sql, new AnimalMapper(), speciesId).stream()
                .collect(Collectors.toList());

        return results;
    }



    @Override
    public Animal add() {
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
