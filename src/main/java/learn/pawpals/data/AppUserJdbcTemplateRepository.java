package learn.pawpals.data;

import learn.pawpals.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {


    private final JdbcTemplate jdbcTemplate;

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public AppUser create(AppUser user) {
        return null;
    }

    @Override
    public boolean update(AppUser user) {
        return false;
    }
}
