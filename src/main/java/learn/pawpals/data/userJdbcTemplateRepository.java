package learn.pawpals.data;

import learn.pawpals.models.user;

import java.util.List;

public class userJdbcTemplateRepository implements userRepository {

    //findAll
    //findByRole
    //add
    //update
    //delete

    @Override
    public List<user> findAll() {
        return null;
    }

    @Override
    public List<user> findByRole() {
        return null;
    }

    @Override
    public user add() {
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
