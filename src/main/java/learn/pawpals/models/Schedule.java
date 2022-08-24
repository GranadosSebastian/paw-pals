package learn.pawpals.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Schedule {

    private int scheduleId;
    private LocalDateTime dateTime;

    // variables from other models
    private int animalId;

    private int appUserId;

    // empty constructor
    public Schedule() {
    }

    // constructor

    public Schedule(int scheduleId, LocalTime dateTime, int animalId, int userId) {
        this.scheduleId = scheduleId;
        this.dateTime = LocalDateTime.from(dateTime);
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule that = (Schedule) o;
        return scheduleId == that.scheduleId && dateTime == that.dateTime && animalId == that.animalId && appUserId == that.appUserId;
    }
    @Override
    public int hashCode() { return Objects.hash(scheduleId, dateTime, animalId, appUserId); }

}
