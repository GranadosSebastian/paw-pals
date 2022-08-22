package learn.pawpals.data;

import learn.pawpals.models.User;

import java.util.List;

public interface UserRepository {

    //findAll
    //findByRole
    //add
    //update
    //delete

    List<User> findAll();

    List<User> findByRole();

    User add();

    boolean update();

    boolean delete();
}
