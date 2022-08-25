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

    List<Schedule> findByAnimal(int animalId);

    List<Schedule> findByAdopter(int appUserId);

    Schedule add(Schedule schedule);

    boolean update(Schedule schedule);

    boolean delete(int scheduleId);

}
