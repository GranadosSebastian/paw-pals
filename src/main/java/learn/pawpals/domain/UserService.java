package learn.pawpals.domain;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.data.UserRepository;
import learn.pawpals.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() throws DataAccessException {
        return userRepository.findAll();
    }

    public List<User> findByRole(int roleId) throws DataAccessException {
        return userRepository.findByRole(roleId);
    }

    public Result<User> add(User user) throws DataAccessException {
        Result<User> result = validate(user);

        if (user != null && user.getUserId() > 0) {
            result.addErrorMessage("User ID should not be set.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            user = userRepository.add(user);
            result.setPayload(user);
        }

        return result;
    }

    public Result<User> update(User user) throws DataAccessException {
        Result<User> result = validate(user);

        if (user.getUserId() <= 0) {
            result.addErrorMessage("User ID is required.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            if (userRepository.update(user)) {
                result.setPayload(user);
            } else {
                result.addErrorMessage("User ID %s was not found.", ResultType.NOT_FOUND, user.getUserId());
            }
        }

        return result;
    }

    public Result<User> delete(int userId) throws DataAccessException {
        Result<User> result = new Result<>();
        if (userRepository.delete(userId)) {
            result.addErrorMessage("User ID %s was not found.", ResultType.NOT_FOUND, userId);
        }
        return result;
    }

    private Result<User> validate(User user) throws DataAccessException {
        Result<User> result = new Result<>();
        //if conditions & validations
        return result;
    }

}
