package learn.pawpals.domain;

import learn.pawpals.data.scheduleRepository;
import learn.pawpals.models.schedule;
import java.util.List;

public class scheduleService {

    private final scheduleRepository scheduleRepository;

    public scheduleService(scheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //findAll
    //findByDate
    //findByAnimal
    //findByAdopter
    //add
    //update
    //delete

    public List<schedule> findAll() {
        return null;
    }

    public List<schedule> findByDate() {
        return null;
    }

    public List<schedule> findByAnimal() {
        return null;
    }

    public List<schedule> findByAdopter() {
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
