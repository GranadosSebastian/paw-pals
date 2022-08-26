package learn.pawpals.data;

import learn.pawpals.data.mappers.AppUserMapper;
import learn.pawpals.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.GeneralPath;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {


    private final JdbcTemplate jdbcTemplate;

    private final String APPUSERCOLS = " username, password_hash, disabled," +
            "first_name, last_name, address, phone ";

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AppUser> findAll() {

        List<String> roles = getRolesByAppUserId();
        final String sql = "select app_user_id," + APPUSERCOLS + "from app_user;";

        return jdbcTemplate.query(sql, new AppUserMapper(roles));
    }
    @Transactional
    @Override
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);

        final String sql = "select app_user_id," + APPUSERCOLS + "from app_user " +
                "where username = ?;";
        return jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }
 // " app_user_id, username, password_hash, disabled," +
 //            "first_name, last_name, address, phone "
    @Transactional
    @Override
    public AppUser add(AppUser user) {

        final String sql = "insert into app_user (" + APPUSERCOLS + ") " +
                "values (?, ?, ?, ?, ?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isEnabled());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getPhone());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setAppUserId((keyHolder.getKey().intValue()));
        updateRoles(user);

        return user;
    }

    @Transactional
    @Override
    public boolean update(AppUser user) {

        final String sql = "update app_user set " +
                            "username = ?," +
                            "disabled = ? " +
                            "where app_user_id = ?";
        boolean updated = jdbcTemplate.update(sql, user.getUsername(), !user.isEnabled(), user.getAppUserId()) > 0;

        if(updated) {
            updateRoles(user);
        }

        return updated;
    }



        @Override
        public boolean delete(int appUserId) {
            jdbcTemplate.update("delete from animal where app_user_id = ?", appUserId);
            return jdbcTemplate.update("delete from `user` where app_user_id = ?", appUserId) > 0;
        }

    private List<String> getRolesByUsername(String username) {
        final String sql = """
                select
                    r.name
                from app_user_role ur
                inner join app_role r on ur.app_role_id = r.app_role_id
                inner join app_user au on ur.app_user_id = au.app_user_id
                where au.username = ?;
                """;
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);
    }

    private List<String> getRolesByAppUserId() {
        final String sql = """
                select
                    r.name
                from app_user_role ur
                inner join app_role r on ur.app_role_id = r.app_role_id
                inner join app_user au on ur.app_user_id = au.app_user_id
                order by ur.app_user_id;
               
                """;
        return jdbcTemplate.query(sql,(rs, rowId) -> rs.getString("name") );
    }

    private void updateRoles(AppUser user) {
        // delete all roles, then re-create
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?;", user.getAppUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (String role : AppUser.convertAuthoritiesToRoles(authorities)) {
            String sql = """
                    insert into app_user_role (app_user_id, app_role_id)
                    select
                        ?,
                        app_role_id
                    from app_role where `name` = ?;
                    """;
            jdbcTemplate.update(sql, user.getAppUserId(), role);
        }
    }
}
