package learn.pawpals.models;

import java.time.LocalTime;
import java.util.Objects;

public class Schedule {

    private int scheduleId;
    private LocalTime time;

    // variables from other models
    private int animalId;

    private int userId;

    // empty constructor
    public Schedule() {
    }

    // constructor
    public Schedule(int scheduleId, LocalTime time, int animalId, int userId) {
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

    public int getAnimalId() {
        return animalId;
    }

    public int getUserId() {
        return userId;
    }

    // setters
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule that = (Schedule) o;
        return scheduleId == that.scheduleId && time == that.time && animalId == that.animalId && userId == that.userId;
    }
    @Override
    public int hashCode() { return Objects.hash(scheduleId, time, animalId, userId); }

}
