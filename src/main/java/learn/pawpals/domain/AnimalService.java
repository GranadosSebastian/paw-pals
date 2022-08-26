package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.AnimalRepository;
import learn.pawpals.models.Animal;
import learn.pawpals.models.Species;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private LocalDate today = LocalDate.now();
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAll() throws DataAccessException {
        return animalRepository.findAll();
    }

    public List<Animal> findBySpecies(String speciesString) throws DataAccessException {

        return animalRepository.findBySpecies(speciesString);
    }

    public Result<Animal> add(Animal animal) throws DataAccessException {
        Result<Animal> result = validate(animal);

        if (animal != null && animal.getAnimalId() > 0) {
            result.addErrorMessage("Animal id should not be set.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            animal = animalRepository.add(animal);
            result.setPayload(animal);
        }

        return result;
    }

    public Result<Animal> update(Animal animal) throws DataAccessException {
        Result<Animal> result = validate(animal);

        if (animal.getAnimalId() <= 0) {
            result.addErrorMessage("Animal ID is required.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            if (animalRepository.update(animal)) {
                result.setPayload(animal);
            } else {
                result.addErrorMessage("Animal ID %s was not found.", ResultType.NOT_FOUND, animal.getAnimalId());
            }
        }
        return result;
    }

    public Result<Animal> delete(int animalId) throws DataAccessException {
        Result<Animal> result = new Result<>();
        if (!animalRepository.deleteById(animalId)) {
            result.addErrorMessage("Animal ID %s was not found.", ResultType.NOT_FOUND, animalId);
        }
        return result;
    }

    private Result<Animal> validate(Animal animal) {
        Result<Animal> result = new Result<>();

        if (animal == null) {
            result.addErrorMessage("Animal cannot be null.", ResultType.INVALID);
            return result;
        }

        if (animal.getSpecies() == null) {
            result.addErrorMessage("Animal 'species' is required.", ResultType.INVALID);

        }

        if (animal.getAnimalName().isBlank() || animal.getAnimalName() == null) {
            result.addErrorMessage("Animal name cannot be empty.", ResultType.INVALID);
        }


        if (animal.getSize() == null) {
            result.addErrorMessage("Size of animal is required.", ResultType.INVALID);
        }

        if (animal.getArrivalDate().isAfter(today)) {
            result.addErrorMessage("Arrival date cannot be in the future.", ResultType.INVALID);
        }

        if (String.valueOf(animal.getArrivalDate()).isBlank()) {
            result.addErrorMessage("Arrival date is required", ResultType.INVALID);
        }

        if (animal.getAppUserId() <= 0) {
            result.addErrorMessage("User ID is required.", ResultType.INVALID);
        }

        return result;

    }


}
