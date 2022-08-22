package learn.pawpals.domain;

import learn.pawpals.data.ScheduleRepository;
import learn.pawpals.models.Schedule;
import java.util.List;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //findAll
    //findByDate
    //findByAnimal
    //findByAdopter
    //add
    //update
    //delete

    public List<Schedule> findAll() {
        return null;
    }

    public List<Schedule> findByDate() {
        return null;
    }

    public List<Schedule> findByAnimal() {
        return null;
    }

    public List<Schedule> findByAdopter() {
        return null;
    }

    public void add() {
        //result
        //return result
    }

    public void update() {
        //result
        //return result
    }

    public void delete() {
        //result
        //return result
    }

    private void validate() {
        //result
        //if conditions & validations
        //return result
    }

}
