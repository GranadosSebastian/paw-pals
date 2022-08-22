package learn.pawpals.data;

import learn.pawpals.data.AnimalJdbcTemplateRepository;
import learn.pawpals.data.KnownGoodState;
import learn.pawpals.models.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AnimalJdbcRepositoryTest {

    @Autowired
    AnimalJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

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
        List<Animal> animals = repository.findBySpecies(2);
        assertEquals(2, animals.size());
    }
    @Test
    void shouldAddAnimal() {

    }

    @Test
    void shouldNotAdd() {

    }

    @Test
    void shouldUpdateAnimal() {

    }

    @Test
    void shouldDeleteAnimal() {

    }

    @Test
    void shouldNotDeleteAnimal() {

    }

}
