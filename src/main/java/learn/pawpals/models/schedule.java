package learn.pawpals.models;

import java.time.LocalTime;

public class schedule {

    private int scheduleId;
    private LocalTime time;

    // variables from other models
    private animal animalId;

    private user userId;

    // empty constructor
    public schedule() {
    }

    // constructor
    public schedule(int scheduleId, LocalTime time, animal animalId, user userId) {
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

    public animal getAnimalId() {
        return animalId;
    }

    public user getUserId() {
        return userId;
    }

    // setters
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setAnimalId(animal animalId) {
        this.animalId = animalId;
    }

    public void setUserId(user userId) {
        this.userId = userId;
    }
}
