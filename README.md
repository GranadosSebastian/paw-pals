# Paw Pals Animal Rescue

## Mission Statement
A non-profit grassroots app focused on rescuing your local furry friends.

# Project outline

## IntelliJ

### Models
    - Adopter (20 min)
        - adopterId (int)
        - firstName (String)
        - lastName (String)
        - currentPets (String)
        - preferredSpecies (enum)
        - animalId(int)*
    - Animal (20 min)
        - animalId (int)
        - animalName (String)
        - breed (String)
        - age (String)
        - size (enum)
        - arrivalDate (LocalDate)
        - friendliness (String)
        - speciesId (int)
        - pastOwnerId (int)
        - isAvailable (boolean)
    - Employee (20 min)
        - staffId (int)
        - role (String)
        - firstName (String)
        - lastName (String)
        - roleDescription (String)
    - Schedule (20 min)
        - scheduleId (int)
        - time (LocalTime)      
        - adopterId (int)*
        - animalId (int)*
        


### Data
    - AdopterRepository (interface)
    - AdopterJdbcTemplateRepository (3hr with Tests)
        - findAll()
        - add()
        - update()
        - delete()

    - AnimalRepository (interface)
    - AnimalJdbcTemplateRepository (2hr with Tests)
        - findAll()
        - findBySpecies()
        - add()
        - update()
        - delete()

    - EmployeeRepository (interface)
    - EmployeeJdbcTemplateRepository (2hr with Tests)
        - findAll()
        - findByType()
        - add()
        - update()
        - delete()

    - ScheduleRepository (interface)
    - ScheduleJdbcTemplateRepository (3hr with Tests)
        - findAll()
        - findByDate()
        - findByAnimal()
        - findByAdopter()
        - add()
        - update()
        - delete()
### Mappers
    -AdopterMapper (30 min)
    -AnimalMapper (30 min)
    -EmployeeMapper (30 min)
    -ScheduleMapper (30 min)

### Domain
    - AdopterService (1 hour)
        - required info

    - AnimalService (1 hour)
        - required info
        - no duplicates
    
    - EmployeeService (1 hour)
        - required info
    
    - ScheduleService (1 hour)
        - required info
        - future date
        - no overlapping times

    - ResultType (20 min)
    - Response (20 min)

### Controllers
    - GlobalExceptionHandler (10 min)
    - ErrorResponse (10 min)
    - AuthController   //Security (20-30 min)
    - AdopterController (1 hour)
    - AnimalController (1 hour)
    - EmployeeController (1 hour)
    - ScheduleController (1 hour)


    - 
### Data Testing (time estimates with Data layer)
    - AdopterJdbcRepositoryTest
        - shouldFindAll()
        - shouldAddAdopter()
        - shouldNotAdd()
        - shouldUpdate()
        - shouldDelete()
        - shouldNotDeleteMissingAdopter()
    - AnimalJdbcRepositoryTest
        - shouldFindAll()
        - shouldFindSpecies()
        - shouldAddAnimal()
        - shouldNotAdd()
        - shouldUpdateAnimal()
        - shouldDeleteAnimal()
        - shouldNotDeleteAnimal()
    - EmployeeJdbcRepositoryTest
        - shouldFindAll()
        - shouldFindAllVets()
        - shouldAdd()
        - shouldNotAdd()
        - shouldUpdate()
        - shouldDelete()
        - shouldNotDelete()
    - ScheduleJdbcRepositoryTest
        - shouldFindAll()
        - shouldFindByDate()
        - shouldFindByAnimal()
        - shouldFindByAdopter()
        - shouldAdd()
        - shouldNotAdd()
        - shouldUpdate()
        - shouldDelete()
        - shouldNotDelete()
    - AdopterJdbcRepositoryDouble (10 min)
    - AnimalJdbcRepositoryDouble    (20 min)
    - EmployeeJdbcRepositoryDouble  (10 min)
    - ScheduleJdbcRepositoryDouble  (10 min)

### Domain Testing
    - AdopterServiceTest
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - AnimalServiceTest
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - EmployeeServiceTest
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - ScheduleServiceTest
        - shouldNotAddNull()
        - shouldNotAddNullName()
        - shouldNotAddPastDate()
        - shouldNotAddOverlappingDates

### Security
    - AppUserService    
    - CorsConfig
    - JwtConverter
    - JwtRequestFilter
    - SecurityConfig


## React/Front End

### Components (Skeleton only/ w routes 3hrs)
    - Home.js (1-2 hrs)   
    - Adopt.js (2hrs)
    - Login.js (30 mins)
    - AdopterForm.js (2-3 hrs)
    - EmployeeForm.js (2-3 hrs)
    - NavBar.js (1.5 hrs)
    - Errors.js (2 hrs) 
    - PetForm.js (2-3 hrs)


### Context
    -UserContext

### Services
    -authApi
    -animalApi
    -adopterApi
    -employeeApi
    -scheduleApi


### MySQL/Database (1hr)

### AnimalsTable
    - animal_id pk, int, not null
    - animal_name varchar(50), not null
    - breed varchar(50), null
    - age int, null
    - size char(10) not null
    - arrival_date, date, not null
    - friendliness_level, char(10) null
    - is_adopted, bit(0) null

    - fk
    - species_id int, not null
    - past_owner_id int, null

### AdoptersTable
    - adopter_id pk, int, not null
    - first_name varchar(50), not null
    - last_name varchar(50), not null
    - current_pets varchar(100), null
    - preferred_species varchar(100), null

    -fk
    - animal_id int, null

### ScheduleTable
    - schedule_id, int, not null pk

    - fk
    - adopter_id, int, not null
    - time(use date/time format), date, not null
    - animal_id int, not null

### Employee_TypeTable
    - staff_id, int, not null, pk
    - role, varchar(50), not null
    - first_name, varchar(50), not null
    - last_name, varchar(50), not null
    - role_description, varchar(50), null

### mySQL Test Data (30 min)
    - 2 species (cat, dog)
    - 2 roles (vet, admin)
    - 5 animals (Bella- mixed small, Missy Calico Cat, Zazu European Shorthair cat, Bruno chihuahua, Rocky chihuahua
    - 3 adopters (Adel, Dex, Corbin)
    - 3 employees (Angie, Sebastian, Keri)
    - 3 scheduled appointments ()
    - 