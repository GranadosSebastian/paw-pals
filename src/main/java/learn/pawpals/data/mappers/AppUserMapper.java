package learn.pawpals.data.mappers;

import learn.pawpals.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppUserMapper implements RowMapper<AppUser> {
    private final List<String> roles;
    public AppUserMapper(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AppUser(

        rs.getInt("app_user_id"),
        rs.getString("username"),
        rs.getString("password_hash"),
        rs.getBoolean("disabled"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        rs.getString("address"),
        rs.getString("phone"),
                roles);

    }
}
