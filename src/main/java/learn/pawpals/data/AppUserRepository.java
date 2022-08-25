package learn.pawpals.data;

import learn.pawpals.models.AppUser;

import java.util.List;

public interface AppUserRepository {

    List<AppUser> findAll();
    AppUser findByUsername(String username);

    AppUser add(AppUser user);


    boolean update(AppUser user);

    boolean delete(int appUserId);

}
