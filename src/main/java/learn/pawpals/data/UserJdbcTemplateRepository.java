package learn.pawpals.data;

import learn.pawpals.models.User;

import java.util.List;

public class UserJdbcTemplateRepository implements UserRepository {

    //findAll
    //findByRole
    //add
    //update
    //delete

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findByRole() {
        return null;
    }

    @Override
    public User add() {
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
