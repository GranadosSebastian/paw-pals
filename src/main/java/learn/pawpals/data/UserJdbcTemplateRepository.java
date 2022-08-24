package learn.pawpals.data;

import learn.pawpals.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FULLUSERSQLCOLS = " user_id, first_name, last_name, address, " +
            "phone, email, role_id ";

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AppUser> findAll() {
        final String sql = "select" + FULLUSERSQLCOLS + "from `user`;";
   //     return jdbcTemplate.query(sql, new UserMapper());
        return null; //edit out
    }

    @Override
    @Transactional
    public List<AppUser> findByRole(int roleId) {
        final String sql = "select" + FULLUSERSQLCOLS +
                "from `user` where role_id = ? ;";

    //    List<AppUser> results = jdbcTemplate.query(sql, new UserMapper(), roleId).stream().toList();

    //    return results;
        return null;
    }

    @Override
    public AppUser add(AppUser appUser) {

        final String sql = "insert into `user` (" + FULLUSERSQLCOLS + ") " +
                "values (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appUser.getFirstName());
            ps.setString(2, appUser.getLastName());
            ps.setString(3, appUser.getAddress());
            ps.setString(4, appUser.getPhone());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        appUser.setAppUserId(keyHolder.getKey().intValue());
        return appUser;
    }

    @Override
    public boolean update(AppUser appUser) {
        final String sql = "update `user` set " +
                "first_name = ?, " +
                "last_name = ?, " +
                "address = ?, " +
                "phone = ?, " +
                "email = ?, " +
                "role_id = ? " +
                "where user_id = ?;";

        return jdbcTemplate.update(sql, appUser.getFirstName(),
                                        appUser.getLastName(),
                                        appUser.getAddress(),
                                        appUser.getPhone(),
                                        appUser.getAppUserId()) > 0;
    }

    @Override
    public boolean delete(int appUserId) {
        jdbcTemplate.update("delete from animal where user_id = ?", appUserId);
        return jdbcTemplate.update("delete from `user` where user_id = ?", appUserId) > 0;
    }

}
