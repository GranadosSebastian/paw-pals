package learn.pawpals.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class schedule {

    private int scheduleId;
    private LocalTime time;

    // variables from other models
    private animals animalId;

    private user userId;

    // empty constructor
    public schedule() {
    }

    // constructor
    public schedule(int scheduleId, LocalTime time, animals animalId, user userId) {
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

    public animals getAnimalId() {
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

    public void setAnimalId(animals animalId) {
        this.animalId = animalId;
    }

    public void setUserId(user userId) {
        this.userId = userId;
    }
}
