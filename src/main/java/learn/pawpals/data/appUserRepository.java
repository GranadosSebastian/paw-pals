package learn.pawpals.data;

import learn.pawpals.models.appUser;

public interface appUserRepository {

    appUser findByUsername(String username);

    appUser create(appUser user);

    boolean update(appUser user);

}
