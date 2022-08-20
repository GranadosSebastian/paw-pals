package learn.pawpals.data;

import learn.pawpals.models.appUser;

public class appUserJdbcTemplateRepository implements appUserRepository {

    /*
    private final JdbcTemplate jdbcTemplate;

    public appUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    */

    @Override
    public appUser findByUsername(String username) {
        return null;
    }

    @Override
    public appUser create(appUser user) {
        return null;
    }

    @Override
    public boolean update(appUser user) {
        return false;
    }
}
