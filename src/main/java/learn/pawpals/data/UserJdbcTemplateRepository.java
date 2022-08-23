package learn.pawpals.data;

import learn.pawpals.data.mappers.UserMapper;
import learn.pawpals.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FULLUSERSQLCOLS = " user_id, first_name, last_name, address, " +
            "phone, email, role_id ";

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select" + FULLUSERSQLCOLS + "from `user`;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    @Transactional
    public List<User> findByRole(int roleId) {
        final String sql = "select" + FULLUSERSQLCOLS +
                "from `user` where role_id = ? ;";

        List<User> results = jdbcTemplate.query(sql, new UserMapper(), roleId).stream().toList();

        return results;
    }

    @Override
    public User add(User user) {

        final String sql = "insert into `user` (" + FULLUSERSQLCOLS + ") " +
                "values (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getRoleId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {
        final String sql = "update `user` set " +
                "first_name = ?, " +
                "last_name = ?, " +
                "address = ?, " +
                "phone = ?, " +
                "email = ?, " +
                "role_id = ? " +
                "where user_id = ?;";

        return jdbcTemplate.update(sql, user.getFirstName(),
                                        user.getLastName(),
                                        user.getAddress(),
                                        user.getPhone(),
                                        user.getEmail(),
                                        user.getRoleId(),
                                        user.getUserId()) > 0;
    }

    @Override
    public boolean delete(int userId) {
        jdbcTemplate.update("delete from animal where user_id = ?", userId);
        return jdbcTemplate.update("delete from `user` where user_id = ?", userId) > 0;
    }

}
