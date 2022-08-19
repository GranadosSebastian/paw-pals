package learn.pawpals.data.mappers;

import learn.pawpals.models.user;

public class userMapper {

    // implement RowMapper<user>

    public user mapRow() {
        user user = new user();
        // set variables
        return user;
    }

}
