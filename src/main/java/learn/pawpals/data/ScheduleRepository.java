package learn.pawpals.data;

import learn.pawpals.models.Schedule;

import java.util.List;

public interface ScheduleRepository {

    //findAll
    //findByDate
    //findByAnimal
    //findByAdopter
    //add
    //update
    //delete

    List<Schedule> findAll();
    List<Schedule> findByDate();

    List<Schedule> findByAnimal();

    List<Schedule> findByAdopter();

    Schedule add();

    boolean update();

    boolean delete();

}
