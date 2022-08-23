package learn.pawpals.data;

import learn.pawpals.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserJdbcRepositoryTest {

    @Autowired
    private UserJdbcTemplateRepository repository;

    @Autowired
    private KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindAll() {
        List<User> users = repository.findAll();
        assertNotNull(users);
        assertTrue(users.size() == 6);

    }

    @Test
    void shouldFindAllFosterers() {
        List<User> users = repository.findByRole(3);
        assertEquals(2, users.size());

    }

    @Test
    void shouldAdd() {
        User user = new User();
        user.setFirstName("Testing");
        user.setLastName("TestingLastName");
        user.setEmail("123@gmail.com");
        user.setRoleId(1);
        User actual = repository.add(user);

        assertNotNull(actual);
        assertEquals("Testing", actual.getFirstName());

    }

    @Test
    void shouldUpdateUser() {
        User user = new User();
        user.setUserId(6);
        user.setAddress("9876 Test Lane");
        assertTrue(repository.update(user));

    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(5));
        assertFalse(repository.delete(5));

    }

}
