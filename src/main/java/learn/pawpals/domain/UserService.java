package learn.pawpals.domain;

import learn.pawpals.data.UserRepository;
import learn.pawpals.models.User;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //findAll
    //findByRole
    //add
    //update
    //delete

    public List<User> findAll() {
        return null;
    }

    public List<User> findByRole(){
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
