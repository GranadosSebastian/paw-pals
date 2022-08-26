package learn.pawpals.data;

import learn.pawpals.data.mappers.ScheduleMapper;
import learn.pawpals.models.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ScheduleJdbcTemplateRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String SCHEDULESQLCOLS = " schedule_id, `time`, app_user_id, animal_id ";

    public ScheduleJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Schedule> findAll() {

        final String sql = "select schedule_id, " + SCHEDULESQLCOLS + "from `schedule`;";
//        final String sql = """
//                SELECT
//                sch.schedule_id,
//                sch.`datetime`,
//                an.animal_name,
//                appf.first_name AS foster_first,
//                appf.last_name AS foster_last,
//                appa.first_name AS adopter_first,
//                appa.last_name AS adopter_last,
//                appa.phone AS adopter_phone,
//                appf.phone AS foster_phone
//                from `schedule` sch
//                inner join app_user appa on appa.app_user_id = sch.adopter_id
//                inner join animal an on an.animal_id = sch.animal_id
//                inner join app_user appf on appf.app_user_id = an.app_user_id;
//
//                """;
        return jdbcTemplate.query(sql, new ScheduleMapper());

    }

    @Override
    public List<Schedule> findByAnimal(int animalId) {
        final String sql = "select schedule_id, " + SCHEDULESQLCOLS + "from `schedule` " +
                "where animal_id = ?;";
//        final String sql = """
//                SELECT
//                sch.schedule_id,
//                sch.`datetime`,
//                an.animal_name,
//                appf.first_name AS foster_first,
//                appf.last_name AS foster_last,
//                appa.first_name AS adopter_first,
//                appa.last_name AS adopter_last,
//                appa.phone AS adopter_phone,
//                appf.phone AS foster_phone
//                from `schedule` sch
//                inner join app_user appa on appa.app_user_id = sch.adopter_id
//                inner join animal an on an.animal_id = sch.animal_id
//                inner join app_user appf on appf.app_user_id = an.app_user_id
//                where an.animal_id = ?;
//
//                """;
        return jdbcTemplate.query(sql, new ScheduleMapper(), animalId);
    }


    @Override
    public List<Schedule> findByAdopter(int appUserId) {
        final String sql = "select schedule_id, " + SCHEDULESQLCOLS + "from `schedule` " +
                "where app_user_id = ?;";
//        final String sql = """
//                SELECT
//                sch.schedule_id,
//                sch.`datetime`,
//                an.animal_name,
//                appf.first_name AS foster_first,
//                appf.last_name AS foster_last,
//                appa.first_name AS adopter_first,
//                appa.last_name AS adopter_last,
//                appa.phone AS adopter_phone,
//                appf.phone AS foster_phone
//                from `schedule` sch
//                inner join app_user appa on appa.app_user_id = sch.adopter_id
//                inner join animal an on an.animal_id = sch.animal_id
//                inner join app_user appf on appf.app_user_id = an.app_user_id
//                where appa.app_user_id = ?;
//
//                """;

        return jdbcTemplate.query(sql, new ScheduleMapper(), appUserId);
    }

    @Override
    public Schedule add(Schedule schedule) {

<<<<<<< HEAD
        final String sql = "insert into `schedule` (`datetime`, animal_id, adopter_id) " +
                "values (?, ?, ?, ?);";
=======
        final String sql = "insert into `schedule` (" + SCHEDULESQLCOLS + ") " +
                "values (?, ?, ?);";
>>>>>>> deb4d282020566ba86aa3884ac2452cacdcee5f3

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, schedule.getDateTime());
            ps.setInt(2, schedule.getAppUserId());
            ps.setInt(3, schedule.getAnimalId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        schedule.setScheduleId(keyHolder.getKey().intValue());

        return schedule;
    }

    @Override
    public boolean update(Schedule schedule) {
        final String sql = "update `schedule` set " +
                "`time` = ?, " +
                "app_user_id = ?" +
                "animal_id = ? " +
                "where schedule_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                schedule.getDateTime(),
                schedule.getAppUserId(),
                schedule.getAnimalId(),
                schedule.getScheduleId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean delete(int scheduleId) {
        final String sql = "delete from `schedule` where schedule_id = ?;";
        return jdbcTemplate.update(sql, scheduleId) > 0;
    }

}
