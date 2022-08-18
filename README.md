# Paw Pals Animal Rescue

___

## Mission Statement
A non-profit grassroots app focused on rescuing your local furry friends.

# Project outline

## High Level Requirements

___

- The application user is an administrator, adopter, or employee.

- The administrator, adopter may view existing appointments for an adopter.
- The adopter may create an appointment to meet with an available animal.
- The administrator, adopter may edit existing appointments.
- The administrator, adopter may cancel a future appointment.

- The administrator, adopter, or employee may view existing animals that are available.
- The administrator, volunteer may create an animal profile to be available for adoption.
- The administrator, veterinarian, and volunteer may update an animal profile to be available for adoption.
- The administrator may delete an animal profile.

- The administrator has full CRUD for employee table and adopter table.

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
    - AnimalJdbcTemplateRepository (3.5hr with Tests)
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
    - AdopterServiceTest (1hr)
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - AnimalServiceTest (1hr)
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - EmployeeServiceTest (30min)
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - ScheduleServiceTest (45min)
        - shouldNotAddNull()
        - shouldNotAddNullName()
        - shouldNotAddPastDate()
        - shouldNotAddOverlappingDates

### Security
    - AppUserService (1 hour)
    - CorsConfig (1 hour)
    - JwtConverter (1 hour)
    - JwtRequestFilter (1 hour)
    - SecurityConfig (1 hour)

## React/Front End

### Color Palette
    - FF9F1C
    - FFBF69
    - FFFFFF
    - CBF3F0
    - 2EC4B6

### Components (Skeleton only/ w routes 3hrs)
    - Home.js (1-2 hrs)   
    - Adopt.js (2hrs)
    - Login.js (30 mins)
    - AdopterForm.js (2-3 hrs)
    - EmployeeForm.js (2-3 hrs)
    - NavBar.js (1.5 hrs)
    - Errors.js (2 hrs) 
    - PetForm.js (2-3 hrs)

### Styles
    - if Bootstrap(1hr)
    - if Material ui (4hrs)

### Context
    -UserContext

### Services
    -authApi
    -animalApi
    -adopterApi
    -employeeApi
    -scheduleApi


## MySQL/Database (1.5hr)

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
      
### UsersTable
    - user_id
    - first_name
    - last_name
    - address
    - phone
    - email
    - role_id fk
    
### Roles

    - Role_id pk
    - Role_type ( adopter, employee, foster_parent)
    - Role_description
    - user_access_level 
   
### ScheduleTable
    - schedule_id, int, not null pk

    - fk
    - adopter_id, int, not null
    - time(use date/time format), date, not null
    - animal_id int, not null

### mySQL Test Data (1 hr)
    - 2 species (cat, dog)
    - 2 roles (vet, admin)
    - 5 animals (Bella- mixed small, Missy Calico Cat, Zazu European Shorthair cat, Bruno chihuahua, Rocky chihuahua
    - 3 adopters (Adel, Dex, Corbin)
    - 3 employees (Angie, Sebastian, Keri)
    - 3 scheduled appointments ()
    - 


~~~
src
├───main
│   └───java
│       ├───resources
│       │       application.properties
│       │
│       └───learn
│           └───paw-pals
│               │   App.java                                  -- app entry point
│               │
│               ├───data
│               │        DataException.java                          -- data layer custom exception
│               │        AdopterJdbcTemplateRepository.java          -- concrete repository
│               │        AdopterRepository.java                      -- repository interface
│               │        AnimalJdbcTemplateRepository.java
│               │        AnimalRepository.java                        EmployeeJdbcTemplateRepository.java
│               │        EmployeeRepository.java
│               │        ScheduleJdbcTemplateRepository.java
│               │        ScheduleRepository.java
│               │
│               ├───domain
│               │        AdopterService.java                  
│               │        AnimalService.java                 
│               │        EmployeeService.java                 
│               │        ScheduleService.java                 
│               │        Result.java                  
│               │        Response.java                 
│               │
│               ├───models
│               │        Adopter.java                        
│               │        Animal.java
│               │        Employee.java                       
│               │        Schedule.java
│               ├───controllers
│               │        AnimalController.java                         -- all console input/output
│               │        AdopterController.java
│               │        EmployeeController.java
│               │        ScheduleController.java
│               │
│               ├───mappers
│                        AnimalMapper.java
│                        AdopterMapper.java
│                        EmployeeMapper.java
│                        ScheduleMapper.java
│                          
│
│                        
│
│
└───test
└───java
└───learn
└───paw-pals
├───data
│       AdopterJdbcRepositoryTest.java    
│       AdopterRepositoryTestDouble.java  
│       AnimalJdbcRepositoryTest.java    
│       AnimalRepositoryTestDouble.java  
│       EmployeeJdbcRepositoryTest.java    
│       EmployeeRepositoryTestDouble.java  
│       ScheduleJdbcRepositoryTest.java    
│       ScheduleRepositoryTestDouble.java  
│
└───domain
│       AdopterServiceTest.java  
│       AnimalServiceTest.java    
│       EmployeeServiceTest.java  
│       ScheduleServiceTest.java  
│
 
~~~
