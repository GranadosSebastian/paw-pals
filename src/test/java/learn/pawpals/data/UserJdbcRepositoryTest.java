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
public class UserJdbcRepositoryTest {

    @Autowired
    private UserJdbcTemplateRepository repository;

    @Autowired
    private KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindAll() {
        List<AppUser> users = repository.findAll();
        assertNotNull(users);
        assertTrue(users.size() == 6);

    }

    @Test
    void shouldFindAllFosterers() {
        List<AppUser> users = repository.findByRole(3);
        assertEquals(2, users.size());

    }

    @Test
    void shouldAdd() {
        AppUser user = new AppUser();
        user.setFirstName("Testing");
        user.setLastName("TestingLastName");
        AppUser actual = repository.add(user);

        assertNotNull(actual);
        assertEquals("Testing", actual.getFirstName());

    }

    @Test
    void shouldUpdateUser() {
        AppUser user = new AppUser();
        user.setAppUserId(6);
        user.setAddress("9876 Test Lane");
        assertTrue(repository.update(user));

    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(5));
        assertFalse(repository.delete(5));

    }

}
