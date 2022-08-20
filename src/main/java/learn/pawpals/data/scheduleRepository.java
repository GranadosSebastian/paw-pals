package learn.pawpals.data;

import learn.pawpals.models.schedule;

import java.util.List;

public interface scheduleRepository {

    //findAll
    //findByDate
    //findByAnimal
    //findByAdopter
    //add
    //update
    //delete

    List<schedule> findAll();
    List<schedule> findByDate();

    List<schedule> findByAnimal();

    List<schedule> findByAdopter();

    schedule add();

    boolean update();

    boolean delete();

}
