package learn.pawpals.data.mappers;


import learn.pawpals.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class AppUserMapper {


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
        return null;
    }
}
