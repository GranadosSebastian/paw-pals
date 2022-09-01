package learn.pawpals.domain;


import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.ScheduleRepository;
import learn.pawpals.models.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    ScheduleService service;

    @MockBean
    ScheduleRepository repository;

    @Test
    void shouldNotAddNull() throws DataAccessException {
        Schedule schedule = null;
        Result<Schedule> result = service.add(schedule);

        assertFalse(result.isSuccess());

    }

    @Test
    void shouldNotDeleteInvalidId() throws DataAccessException {
        Result<Schedule> result = service.delete(10000);
        assertFalse(result.isSuccess());
    }



}
