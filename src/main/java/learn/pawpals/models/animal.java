package learn.pawpals.models;

import java.time.LocalDate;

public class animal {

    private int animalId;

    private String animalName;

    private String breed;

    private int age;

    private size size;

    private LocalDate arrivalDate;

    private String friendliness;

    private int speciesId;

    private user userId;

    private boolean isAvailable;

    // empty constructor
    public animal() {
    }

    // constructor
    public animal(int animalId, String animalName, String breed, int age, learn.pawpals.models.size size, LocalDate arrivalDate, String friendliness, int speciesId, user userId, boolean isAvailable) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.breed = breed;
        this.age = age;
        this.size = size;
        this.arrivalDate = arrivalDate;
        this.friendliness = friendliness;
        this.speciesId = speciesId;
        this.userId = userId;
        this.isAvailable = isAvailable;
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

    public learn.pawpals.models.size getSize() {
        return size;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getFriendliness() {
        return friendliness;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public user getUserId() {
        return userId;
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

    public void setSize(learn.pawpals.models.size size) {
        this.size = size;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setFriendliness(String friendliness) {
        this.friendliness = friendliness;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public void setUserId(user userId) {
        this.userId = userId;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // object equals
    // hash code

}
