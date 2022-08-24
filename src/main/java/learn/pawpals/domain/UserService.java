package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.UserRepository;
import learn.pawpals.models.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> findAll() throws DataAccessException {
        return userRepository.findAll();
    }

    public Result<AppUser> add(AppUser appUser) throws DataAccessException {
        Result<AppUser> result = validate(appUser);

        if (appUser != null && appUser.getAppUserId() > 0) {
            result.addErrorMessage("User ID should not be set.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            appUser = userRepository.add(appUser);
            result.setPayload(appUser);
        }

        return result;
    }

    public Result<AppUser> update(AppUser appUser) throws DataAccessException {
        Result<AppUser> result = validate(appUser);

        if (appUser.getAppUserId() <= 0) {
            result.addErrorMessage("User ID is required.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            if (userRepository.update(appUser)) {
                result.setPayload(appUser);
            } else {
                result.addErrorMessage("User ID %s was not found.", ResultType.NOT_FOUND, appUser.getAppUserId());
            }
        }

        return result;
    }

    public Result<AppUser> delete(int appUserId) throws DataAccessException {
        Result<AppUser> result = new Result<>();
        if (userRepository.delete(appUserId)) {
            result.addErrorMessage("User ID %s was not found.", ResultType.NOT_FOUND, appUserId);
        }
        return result;
    }

    private Result<AppUser> validate(AppUser appUser) throws DataAccessException {
        Result<AppUser> result = new Result<>();
        //if conditions & validations
        return result;
    }

}
