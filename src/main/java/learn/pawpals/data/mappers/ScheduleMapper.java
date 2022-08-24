package learn.pawpals.data.mappers;

import learn.pawpals.models.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(rs.getInt("schedule_id"));
        schedule.setDateTime(LocalDateTime.parse(rs.getString("date_time")));
        schedule.setUserId(rs.getInt("user_id"));
        schedule.setAnimalId(rs.getInt("schedule_id"));
        return schedule;
    }
}
