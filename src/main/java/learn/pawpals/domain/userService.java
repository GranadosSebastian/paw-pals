package learn.pawpals.domain;

import learn.pawpals.data.userRepository;
import learn.pawpals.models.user;
import java.util.List;

public class userService {

    private final userRepository userRepository;

    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    //findAll
    //findByRole
    //add
    //update
    //delete

    public List<user> findAll() {
        return null;
    }

    public List<user> findByRole(){
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
