package learn.pawpals.data.mappers;

import learn.pawpals.models.User;

public class UserMapper {

    // implement RowMapper<user>

    public User mapRow() {
        User user = new User();
        // set variables
        return user;
    }

}
