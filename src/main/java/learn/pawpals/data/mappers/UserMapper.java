package learn.pawpals.data.mappers;

import learn.pawpals.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAddress(rs.getString("address"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setRoleId(rs.getInt("role_id"));
        return user;
    }
}
