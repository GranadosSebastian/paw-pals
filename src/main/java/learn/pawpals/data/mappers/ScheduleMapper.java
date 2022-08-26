
package learn.pawpals.data.mappers;

import learn.pawpals.models.Schedule;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(rs.getInt("schedule_id"));
<<<<<<< HEAD
        schedule.setDateTime(rs.getTimestamp("datetime").toLocalDateTime());
=======
        schedule.setDateTime(rs.getObject(2, LocalDateTime.class));
>>>>>>> deb4d282020566ba86aa3884ac2452cacdcee5f3
        schedule.setAppUserId(rs.getInt("app_user_id"));
        schedule.setAnimalId(rs.getInt("animal_id"));
        return schedule;
    }
}
