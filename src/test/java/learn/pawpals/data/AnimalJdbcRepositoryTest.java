package learn.pawpals.data;

import learn.pawpals.models.Animal;
import learn.pawpals.models.Size;
import learn.pawpals.models.Species;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AnimalJdbcRepositoryTest {


    @Autowired AnimalJdbcTemplateRepository repository;

    @Autowired
    private KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Animal> animals = repository.findAll();
        assertNotNull(animals);
        assertTrue(animals.size() == 5);

    }

    @Test
    void shouldFindSpecies() {
        List<Animal> animals = repository.findBySpecies(Species.CAT);
        assertEquals(2, animals.size());
    }
    @Test
    void shouldAddAnimal() {
        Animal animal = new Animal();
        animal.setAnimalName("TEST");
        animal.setSize(Size.MEDIUM);
        animal.setArrivalDate(LocalDate.now());
        animal.setSpecies(Species.BIRD);
        animal.setUserId(1);
        Animal actual = repository.add(animal);

        assertNotNull(actual);
        assertEquals("TEST", actual.getAnimalName());

    }


    @Test
    void shouldUpdateAnimal() {
        Animal animal = new Animal();
        animal.setAnimalId(1);
        animal.setAnimalName("Doggo");
        assertTrue(repository.update(animal));

    }

    @Test
    void shouldDeleteAnimal() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }


}
