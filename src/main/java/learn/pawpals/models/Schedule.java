package learn.pawpals.models;

import java.time.LocalTime;

public class Schedule {

    private int scheduleId;
    private LocalTime time;

    // variables from other models
    private Animal animalId;

    private User userId;

    // empty constructor
    public Schedule() {
    }

    // constructor
    public Schedule(int scheduleId, LocalTime time, Animal animalId, User userId) {
        this.scheduleId = scheduleId;
        this.time = time;
        this.animalId = animalId;
        this.userId = userId;
    }

    // getters
    public int getScheduleId() {
        return scheduleId;
    }

    public LocalTime getTime() {
        return time;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public User getUserId() {
        return userId;
    }

    // setters
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setAnimalId(Animal animalId) {
        this.animalId = animalId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    // object equals
    // hash code

}
