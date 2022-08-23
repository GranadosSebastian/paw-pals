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

    List<User> findByRole(int roleId);

    User add(User user);

    boolean update(User user);

    boolean delete(int userId);
}
