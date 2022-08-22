package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.AnimalRepository;
import learn.pawpals.models.Animal;


import java.util.List;


public class AnimalService {

    private final AnimalRepository animalRepository;


    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAll() throws DataAccessException {
        return animalRepository.findAll();
    }

    public List<Animal> findBySpecies(int speciesId) throws DataAccessException {
        return animalRepository.findBySpecies();
    }

    public Result<Animal> add(Animal animal) throws DataAccessException {
        Result<Animal> result = validate(animal);

        if (animal != null && animal.getAnimalId() > 0) {
            result.addErrorMessage("Animal id should not be set.", ResultType.INVALID);
        }
        if (result.isSuccess()) {
       //     animal = animalRepository.add(animal);
            result.setPayload(animal);
        }
        return result;
    }

    public Result<Animal> update(Animal animal) throws DataAccessException {
        Result<Animal> result = validate(animal);
        return result;
    }

    public Result<Animal> delete(Animal animal) throws DataAccessException {
        Result<Animal> result = validate(animal);
        return result;
    }

    private Result<Animal> validate(Animal animal) {
        Result<Animal> result = new Result<>();

        if (animal == null) {
            result.addErrorMessage("Animal cannot be null.", ResultType.INVALID);
            return result;
        }

        //add if conditions & validations

        return result;

    }


}
