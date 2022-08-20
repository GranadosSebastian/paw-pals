package learn.pawpals.data;

import learn.pawpals.models.user;

import java.util.List;

public interface userRepository {

    //findAll
    //findByRole
    //add
    //update
    //delete

    List<user> findAll();

    List<user> findByRole();

    user add();

    boolean update();

    boolean delete();
}
