package learn.pawpals.data;


import learn.pawpals.models.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ScheduleJdbcRepositoryTest {

    @Autowired
    private ScheduleJdbcTemplateRepository repository;

    @Autowired
    private KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<Schedule> schedules = repository.findAll();
        assertNotNull(schedules);
        assertEquals(3, schedules.size());

    }
    @Test
    void shouldFindById2() {
        Schedule schedule = repository.findById(2);
        assertNotNull(schedule);
        assertEquals(1, schedule.getAnimalId());

    }

    @Test
    void shouldFindByAnimal() {
        List<Schedule> schedules = repository.findByAnimal(1);
        assertNotNull(schedules);
        assertEquals(1, schedules.size());

    }

    @Test
    void shouldAdd() {
        Schedule schedule = new Schedule();
        schedule.setDateTime(LocalDateTime.now());
        schedule.setAppUserId(2);
        schedule.setAnimalId(2);
        schedule.setScheduleId(4);
        Schedule actual = repository.add(schedule);

        assertNotNull(actual);
        assertEquals(2, actual.getAnimalId());
        }
  


    @Test
    void shouldUpdate() {

    }

    @Test
    void shouldDelete() {

    }


}
