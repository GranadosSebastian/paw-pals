package learn.pawpals.data;

import learn.pawpals.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppUserJdbcRepositoryTest {

    @Autowired
    private AppUserJdbcTemplateRepository repository;

    @Autowired
    private KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }



    @Test
    void shouldAdd() {


    }

    @Test
    void shouldUpdateUser() {


    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(3));
        assertFalse(repository.delete(3));

    }

}
