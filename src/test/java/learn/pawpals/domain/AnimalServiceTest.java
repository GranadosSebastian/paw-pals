package learn.pawpals.domain;


import learn.pawpals.data.AnimalRepository;
import learn.pawpals.data.DataAccessException;
import learn.pawpals.models.Animal;
import learn.pawpals.models.Size;
import learn.pawpals.models.Species;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AnimalServiceTest {

    @Autowired
    AnimalService service;

    @MockBean
    AnimalRepository repository;

    @Test
    void shouldNotAddNull() throws DataAccessException {
        Animal animal = null;
        Result<Animal> result = service.add(animal);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());

    }

    @Test
    void shouldNotAddNullName() throws DataAccessException {
        Animal animal = new Animal();
        animal.setAnimalName(null);
        animal.setAppUserId(2);
        animal.setAvailable(true);
        animal.setSpecies(Species.dog);
        animal.setSize(Size.SMALL);
        animal.setArrivalDate(LocalDate.now());
        animal.setSpeciesString("Dog");

        Result<Animal> result = service.add(animal);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());



    }

}
