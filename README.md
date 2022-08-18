# Paw Pals Animal Rescue

## Mission Statement
A non-profit grassroots app focused on rescuing your local furry friends.

# Project outline

## IntelliJ

### Models
    - Adopter
    - Animal
    - Employee
    - Schedule

### Data
    - AdopterRepository (interface)
    - AdopterFileRepository
        - findAll()
        - add()
        - update()
        - delete()

    - AnimalRepository (interface)
    - AnimalFileRepository
        - findAll()
        - findBySpecies()
        - add()
        - update()
        - delete()

    - EmployeeRepository (interface)
    - EmployeeFileRepository
        - findAll()
        - findByType()
        - add()
        - update()
        - deete()

    - ScheduleRepository (interface)
    - ScheduleFileRepository
        - findAll()
        - findByDate()
        - findByAnimal()
        - findByAdopter()
        - add()
        - update()
        - delete()

### Domain
    - AdopterService
        - required info

    - AnimalService
        - required info
        - no duplicates
    
    - EmployeeService
        - required info
    
    - ScheduleService
        - required info
        - future date
        - no overlapping times

### Data Testing
    - AdopterFileRepositoryTest
    - AnimalFileRepositoryTest
    - EmployeeFileRepositoryTest
    - SxheduleFileRepositoryTest

