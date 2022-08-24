package learn.pawpals.data;

import learn.pawpals.models.AppUser;

public interface AppUserRepository {

    AppUser findByUsername(String username);

    AppUser add(AppUser user);


    boolean update(AppUser user);

    boolean delete(int appUserId);

}
