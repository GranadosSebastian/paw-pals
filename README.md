# Paw Pals Animal Rescue

___

## Mission Statement
A non-profit grassroots app focused on rescuing your local furry friends.

## Elevator Pitch

Most adopters looking for a pet need to go through the local animal shelter and don't have access to fostered pets. 

Paw Pals Animal Rescue goes above and beyond to provide services to the community
by connecting adopters with foster homes directly. It provides a portal for foster pet parents
to sign up to care for pets waiting to be adopted and for adopters to find the perfect pet companion.

Paw pals can help alleviate the pressure put on shelters by helping more fosters and adopters connect easily.

# Project outline

## High Level Requirements

___

- The application user is an administrator, adopter, or employee.

- The administrator, adopter may view existing appointments for an adopter.
- The adopter may create an appointment to meet with an available animal.
- The foster parent can update and create available times for appointments.
- The administrator, adopter may edit existing appointments.
- The administrator, adopter may cancel a future appointment.

- The administrator, adopter, or employee may view existing animals that are available.
- The administrator, volunteer may create an animal profile to be available for adoption.
- The administrator, veterinarian, and volunteer may update an animal profile to be available for adoption.
- The administrator may delete an animal profile.

- The administrator has full CRUD for employee table and adopter table.

## IntelliJ

### Models

    - Animal (20 min) **Angela**
        - animalId (int)
        - animalName (String)
        - breed (String)
        - age (inti)
        - size (enum)
        - arrivalDate (LocalDate)
        - friendliness (String)
        - speciesId (int)
        - userId (int)
        - isAvailable (boolean)

    - Schedule (20 min) **Angela**
        - scheduleId (int)
        - time (LocalTime)      
        - userId (int)*
        - animalId (int)*

    - User (20min) **Angela**
        - userId (int)
        - firstName (String)
        - lastName (String)
        - address (String)
        - phone (String)
        - email (String)
        - roleId (int)
        


### Data

    - AnimalRepository (interface)
    - AnimalJdbcTemplateRepository (Keri 3.5hr with Tests)
        - findAll()
        - findBySpecies()
        - add()
        - update()
        - delete()

    - UserRepository (interface)
    - UserJdbcTemplateRepository (Keri 2hr with Tests)
        - findAll()
        - findByRole()
        - add()
        - update()
        - delete()

    - ScheduleRepository (interface)
    - ScheduleJdbcTemplateRepository (Keri 3hr with Tests)
        - findAll()
        - findByDate()
        - findByAnimal()
        - findByAdopter()
        - add()
        - update()
        - delete()

### Mappers
    -AnimalMapper (30 min) **Angela**
    -UserMapper (30 min) **Angela**
    -ScheduleMapper (30 min) **Angela**

### Domain

    - AnimalService (1 hour) **Angela**
        - required info
        - no duplicates
    
    - UserService (1 hour) **Angela**
        - required info
    
    - ScheduleService (1 hour) **Angela**
        - required info
        - future date
        - no overlapping times

    - ResultType (20 min) **Angela**
    - Response (20 min) **Angela**

### Controllers
    - GlobalExceptionHandler (10 min) **Angela**
    - ErrorResponse (10 min) **Angela**
    - AuthController   //Security (20-30 min) **Angela**
    - AnimalController (1 hour) **Angela**
    - UserController (1 hour) **Angela**
    - ScheduleController (1 hour) **Angela**


    - 
### Data Testing (time estimates with Data layer)(Keri )

    - AnimalJdbcRepositoryTest
        - shouldFindAll()
        - shouldFindSpecies()
        - shouldAddAnimal()
        - shouldNotAdd()
        - shouldUpdateAnimal()
        - shouldDeleteAnimal()
        - shouldNotDeleteAnimal()
    - UserJdbcRepositoryTest
        - shouldFindAll()
        - shouldFindAllFosterers()
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
    - AnimalJdbcRepositoryDouble    (Keri 20 min)
    - UserJdbcRepositoryDouble  (Keri 10 min)
    - ScheduleJdbcRepositoryDouble  (Keri 10 min)

### Domain Testing

    - AnimalServiceTest (Keri 1hr)
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - UserServiceTest (Keri 30min)
        - shouldNotAddNull()
        - shouldNotAddNullName()

    - ScheduleServiceTest (Keri 45min)
        - shouldNotAddNull()
        - shouldNotAddNullName()
        - shouldNotAddPastDate()
        - shouldNotAddOverlappingDates

### Security
    - AppUserService (Sebastian 1 hour)
    - CorsConfig (Sebastian 1 hour)
    - JwtConverter (Sebastian 1 hour)
    - JwtRequestFilter (Sebastian 1 hour)
    - SecurityConfig (Sebastian 1 hour)


## React/Front End

### Color Palette
    - FF9F1C
    - FFBF69
    - FFFFFF
    - CBF3F0
    - 2EC4B6

### Components (Skeleton only/ w routes 3hrs)
    - Home.js (Sebastian 1-2 hrs)   
    - Adopt.js (Sebastian 2hrs)
    - Login.js (Sebastian 30 mins)
    - UserForm.js (Sebastian 2-3 hrs)
    - NavBar.js (Sebastian 1.5 hrs)
    - Errors.js (Sebastian 2 hrs) 
    - PetForm.js (Sebastian 2-3 hrs)

### Styles
    - if Bootstrap(Sebastian 1hr)
    - if Material ui (Sebastian 4hrs)


### Context
    -UserContext

### Services
    -authApi
    -animalApi
    -adopterApi
    -employeeApi
    -scheduleApi


## MySQL/Database (Keri 1.5hr)

### AnimalsTable
    - animal_id pk, int, not null
    - animal_name varchar(50), not null
    - breed varchar(50), null
    - age int, null
    - size char(10) not null
    - arrival_date, date, not null
    - friendliness_level, char(10) null
    - is_available, bit(0) null

    - fk
    - species_id int, not null
    - user_id int, not null

### ScheduleTable
    - schedule_id, int, not null pk
    - time, datetime, not null

    - fk
    - adopter_id, int, not null
    - animal_id int, not null

<<<<<<< HEAD
### LoginTable
    - login_id, pk
    - username, varchar(50), not null
    - password, varchar(50), not null
    
    -fk
    - user_id, int, not null
=======
### UserTable
    - user_id, int, not null, pk
    - first_name, varchar(50), not null
    - last_name, varchar(50), not null
    - address, varchar(100), null
    - phone, varchar(16), null
    - email, varchar(50), not null
    
    - fk
    - role_id, int, not null

### SpeciesTable
    - species_id, int, not null, pk
    - species_name, varchar(25) not null

### RolesTable
    - role_id, int, not null, pk
    - role_type, varchar(50), not null
    - description, varchar(100)
    - user_access_level, varchar(15), not null
>>>>>>> d227e197f2ceadfed91337ab84b07173b6e58314


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
│               │        UserJdbcTemplateRepository.java          -- concrete repository
│               │        UserRepository.java                      -- repository interface
│               │        AnimalJdbcTemplateRepository.java
│               │        AnimalRepository.java                        EmployeeJdbcTemplateRepository.java
│               │        ScheduleJdbcTemplateRepository.java
│               │        ScheduleRepository.java
│               │
│               ├───domain               
│               │        AnimalService.java                 
│               │        UserService.java                 
│               │        ScheduleService.java                 
│               │        Result.java                  
│               │        Response.java                 
│               │
│               ├───models                    
│               │        Animal.java
│               │        User.java                       
│               │        Schedule.java
│               ├───controllers
│               │        AnimalController.java                         -- all console input/output
│               │        UserController.java
│               │        ScheduleController.java
│               │
│               ├───mappers
│                        AnimalMapper.java
│                        UserMapper.java
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


## Deadlines

___

# Monday morning 22nd
 - Sql tables complete
 - Java Skeleton complete
 - React/UI skeleton complete
 - have list of dependencies needed to download

# Tuesday morning/evening if needed 23rd
 - Animal Table slice complete from front to back / CRUD
 - animal form
 - display pet
 
# Wednesday noon 
 - User slice / CRUD
 - user from 
 - signup

# Friday morning
 - Security complete
 - login 

# Monday morning 29th (goal friday afternoon)
 - Scheduling
 - schedule form

# Tuesday morning 30th 
 - Debugging complete
 - AWS started

# Wednesday morning 31st
 - deployed on AWS 
 - presentation practice started

# Thursday morning 1st
 - project complete/ buffer day 
 - presentation practice


