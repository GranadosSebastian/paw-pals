package learn.pawpals.models;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;


public class Animal {

    private int animalId;

    private String animalName;

    private String breed;

    private int age;

    private Size size;

    private LocalDate arrivalDate;

    private String friendliness;

    private Species species;

    private int appUserId;

    private boolean isAvailable;

    private AppUser appUser;

    // empty constructor
    public Animal() {
    }

    // constructor
    public Animal(int animalId, String animalName, String breed, int age, Size size,
                  LocalDate arrivalDate, String friendliness, Species species,
                  int appUserId, boolean isAvailable, AppUser appUser) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.breed = breed;
        this.age = age;
        this.size = size;
        this.arrivalDate = arrivalDate;
        this.friendliness = friendliness;
        this.species = species;
        this.appUserId = appUserId;
        this.isAvailable = isAvailable;
        this.appUser = appUser;
    }

    // getters
    public int getAnimalId() {
        return animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public Size getSize() {
        return size;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getFriendliness() {
        return friendliness;
    }

    public Species getSpecies() {
        return species;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // setters
    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setFriendliness(String friendliness) {
        this.friendliness = friendliness;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public void setAppUserId(int userId) {
        this.appUserId = appUserId;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal that = (Animal) o;
        return animalId == that.animalId && animalName == that.animalName && breed == that.breed && age == that.age && size == that.size
                && arrivalDate == that.arrivalDate && friendliness == that.friendliness && isAvailable == that.isAvailable
                && species == that.species && appUserId == that.appUserId && appUser == that.appUser;
    }
    @Override
    public int hashCode() { return Objects.hash(animalId, breed, age, size, arrivalDate, friendliness, isAvailable, species, appUserId, appUser); }
}