package learn.pawpals.data;

import learn.pawpals.App;
import learn.pawpals.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
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
    void shouldFindByUsername() {
        AppUser user = repository.findByUsername("saladdressing@aol.com");
        assertNotNull(user);
        assertEquals(6, user.getAppUserId());
    }
    @Test
    void shouldAdd() {
        AppUser user = new AppUser();
        String username = "Take 2";
        String password = "$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa";

        user.getUsername().equals(username);
        user.getPassword().equals(password);
        user.isEnabled();
        user.setFirstName("Test");
        user.setLastName("TestLast");
        user.setAddress("123 nsodd");
        user.setPhone("12345687");


        ArrayList<String> roles = new ArrayList<>(1);
        roles.add(0, "staff");

        user.setRoles(roles);

        AppUser actual = repository.add(user, roles);

        assertNotNull(actual);
        assertEquals("Test", actual.getFirstName());

    }

    @Test
    void shouldUpdateUser() {
        AppUser user = new AppUser();
        user.setPhone("5675675678");
        user.setAddress("123 New Address");
        user.setFirstName("Sebastian");
        user.setLastName("Granados");
        user.isEnabled();
        user.setAppUserId(5);

        assertTrue(repository.update(user));

    }

}
