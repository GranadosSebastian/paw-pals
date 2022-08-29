package learn.pawpals.data;

import learn.pawpals.App;
import learn.pawpals.models.AppUser;

import java.util.List;

public interface AppUserRepository {

    List<AppUser> findAll();

    AppUser findById(int appUserId);

    AppUser findByUsername(String username);

    AppUser add(AppUser user);

    boolean update(AppUser user);

    boolean delete(int appUserId);

}
