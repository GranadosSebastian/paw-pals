package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.ScheduleRepository;
import learn.pawpals.models.Animal;
import learn.pawpals.models.Schedule;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAll() throws DataAccessException {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findByAnimal(int animalId) throws DataAccessException {

          return scheduleRepository.findByAnimal(animalId);
    }

    public List<Schedule> findByAdopter(int appUserId) throws DataAccessException {

        return scheduleRepository.findByAnimal(appUserId);
    }

    public Result<Schedule> add(Schedule schedule) throws DataAccessException {
        Result<Schedule> result = validate(schedule);
        if (schedule != null && schedule.getScheduleId() > 0) {
            result.addErrorMessage("Schedule id should not be set.", ResultType.INVALID);
        }
        if (result.isSuccess()) {
            schedule = scheduleRepository.add(schedule);
            result.setPayload(schedule);
        }
        return result;
    }

    public Result<Schedule> update(Schedule schedule) throws DataAccessException {
        Result<Schedule> result = validate(schedule);
        if (schedule.getScheduleId() <= 0) {
            result.addErrorMessage("Schedule ID is required.", ResultType.INVALID);
        }
        if (result.isSuccess()) {
            if (scheduleRepository.update(schedule)) {
                result.setPayload(schedule);
            } else {
                result.addErrorMessage("Schedule ID %s was not found.", ResultType.NOT_FOUND, schedule.getScheduleId());
            }
        }
        return result;
    }

    public Result<Schedule> delete(int scheduleId) throws DataAccessException {
        Result<Schedule> result = new Result();
        if (!scheduleRepository.delete(scheduleId)) {
            result.addErrorMessage("Schedule ID %s was not found.", ResultType.NOT_FOUND, scheduleId);
        }
        return result;
    }

    private Result<Schedule> validate(Schedule schedule) throws DataAccessException {
        Result<Schedule> result = new Result();

        if (schedule == null) {
            result.addErrorMessage("Schedule cannot be null.", ResultType.INVALID);
            return result;
        }
        if (String.valueOf(schedule.getDateTime()).isBlank()) {
            result.addErrorMessage("Date and time are required", ResultType.INVALID);
        }
        if (schedule.getAnimalId() <= 0) {
            result.addErrorMessage("Animal ID is required.", ResultType.INVALID);
        }
        if (schedule.getAppUserId() <= 0) {
            result.addErrorMessage("User ID is required.", ResultType.INVALID);

        }

        return result;
    }



}


