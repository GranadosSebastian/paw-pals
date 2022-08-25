package learn.pawpals.data;

import learn.pawpals.models.Animal;
import learn.pawpals.models.Size;
import learn.pawpals.models.Species;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnimalJdbcRepositoryTest {


    @Autowired AnimalJdbcTemplateRepository repository;

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
        assertEquals(5, animals.size());

    }

    @Test
    void shouldFindSpecies() {
        List<Animal> animals = new ArrayList<>();
        animals = repository.findBySpecies(Species.cat);
        assertEquals(2, animals.size());
    }
    @Test
    void shouldAddAnimal() {
        Animal animal = new Animal();
        animal.setAnimalName("TEST");
        animal.setSize(Size.SMALL);
        animal.setArrivalDate(LocalDate.now());
        animal.setSpecies(Species.bird);
        animal.setAppUserId(1);
        Animal actual = repository.add(animal);

        assertNotNull(actual);
        assertEquals("TEST", actual.getAnimalName());

    }


    @Test
    void shouldUpdateAnimal() {
        Animal animal = new Animal();
        animal.setAnimalId(1);
        animal.setAnimalName("Doggo");
        animal.setSize(Size.MEDIUM);
        animal.setArrivalDate(LocalDate.now());
        animal.setAvailable(true);
        animal.setSpecies(Species.dog);
        assertTrue(repository.update(animal));

    }

    @Test
    void shouldDeleteAnimal() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }


}
