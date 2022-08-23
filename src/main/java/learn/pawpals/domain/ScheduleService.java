package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.ScheduleRepository;
import learn.pawpals.models.Animal;
import learn.pawpals.models.Schedule;

import javax.xml.crypto.Data;
import java.util.List;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAll() throws DataAccessException {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findByDate() throws DataAccessException {
        return scheduleRepository.findByDate();
    }

    public List<Schedule> findByAnimal() throws DataAccessException {

        return scheduleRepository.findByAnimal();
    }

    public List<Schedule> findByAdopter() throws DataAccessException {

        return scheduleRepository.findByAdopter();
    }

    public Result<Schedule> add(Schedule schedule) {
        Result<Schedule> result = validate(schedule);
        if (schedule != null && schedule.getScheduleId() > 0) {
            result.addErrorMessage("Schedule id should not be set.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            schedule = scheduleRepository.add(schedule);
            result.setPayload(schedule);
        }
    }

    public Result<Schedule> update(Schedule schedule) throws DataAccessException {
        Result<Schedule> result = validate(schedule);
        //return result
    }

    public Result<Schedule> delete(int scheduleId) throws DataAccessException {
        Result<Schedule> result = new Result();
        if (scheduleRepository.delete(scheduleId)) {
            result.addErrorMessage("Animal ID %s was not found.", ResultType.NOT_FOUND, animalId);
        }
        return result;
    }

    private Result<Schedule> validate(Schedule schedule) throws DataAccessException {
        Result<Schedule> result = new Result();
        //if conditions & validations
        return result;
    }

}
