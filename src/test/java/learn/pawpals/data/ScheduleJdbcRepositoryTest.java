package learn.pawpals.data;


import learn.pawpals.models.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

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
    void shouldFindByDate() {

    }

    @Test
    void shouldFindByAnimal() {

    }

    @Test
    void shouldFindByAdopter() {

    }

    @Test
    void shouldAdd() {

    }


    @Test
    void shouldUpdate() {

    }

    @Test
    void shouldDelete() {

    }


}
