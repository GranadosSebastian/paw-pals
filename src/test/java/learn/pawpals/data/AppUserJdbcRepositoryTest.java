package learn.pawpals.data;

import learn.pawpals.App;
import learn.pawpals.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

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
    void shouldFindAll() {
        List<AppUser> appUsers = repository.findAll();
        assertNotNull(appUsers);
        assertEquals(6, appUsers.size());
    }
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
