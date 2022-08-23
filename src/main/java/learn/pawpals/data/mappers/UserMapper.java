package learn.pawpals.data.mappers;

import learn.pawpals.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setAppUserId(rs.getInt("user_id"));
        appUser.setFirstName(rs.getString("first_name"));
        appUser.setLastName(rs.getString("last_name"));
        appUser.setAddress(rs.getString("address"));
        appUser.setPhone(rs.getString("phone"));
        appUser.setEmail(rs.getString("email"));
        appUser.setRoleId(rs.getInt("role_id"));
        return appUser;
    }
}
