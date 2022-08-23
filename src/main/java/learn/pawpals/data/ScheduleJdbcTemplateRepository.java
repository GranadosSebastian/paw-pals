package learn.pawpals.data;

import learn.pawpals.models.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleJdbcTemplateRepository implements ScheduleRepository {

    //findAll
    //findByDate
    //findByAnimal
    //findByAdopter
    //add
    //update
    //delete

    @Override
    public List<Schedule> findAll() {
        return null;
    }

    @Override
    public List<Schedule> findByTime() {
        return null;
    }

    @Override
    public List<Schedule> findByAnimal() {
        return null;
    }

    @Override
    public List<Schedule> findByAdopter() {
        return null;
    }

    @Override
    public Schedule add() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

}
