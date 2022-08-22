package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.AnimalRepository;
import learn.pawpals.models.Animal;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;


    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAll() throws DataAccessException {
        return animalRepository.findAll();
    }

    public List<Animal> findBySpecies(int speciesId) throws DataAccessException {

        return animalRepository.findBySpecies(speciesId);
    }



    private void validate() {
        //result
        //if conditions & validations
        //return result

        return animalRepository.findBySpecies();
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
        if (animalRepository.delete(animalId)) {
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

        if (animal.getSpeciesId() <= 0) {
            result.addErrorMessage("Animal 'species' is required", ResultType.INVALID);

        }
        //add if conditions & validations

        return result;

    }


}
