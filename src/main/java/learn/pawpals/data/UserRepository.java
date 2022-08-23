package learn.pawpals.data;

import learn.pawpals.models.AppUser;

import java.util.List;

public interface UserRepository {

    List<AppUser> findAll();

    List<AppUser> findByRole(int roleId);

    AppUser add(AppUser appUser);

    boolean update(AppUser appUser);

    boolean delete(int appUserId);
}
