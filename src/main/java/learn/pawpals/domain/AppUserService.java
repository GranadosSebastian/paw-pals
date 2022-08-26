package learn.pawpals.domain;

import learn.pawpals.App;
import learn.pawpals.data.AppUserRepository;
import learn.pawpals.data.DataAccessException;
import learn.pawpals.domain.Result;
import learn.pawpals.domain.ResultType;
import learn.pawpals.models.Animal;
import learn.pawpals.models.AppUser;
import learn.pawpals.models.Credentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository repository;
    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository repository,
                          PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public List<AppUser> findAll() throws DataAccessException {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);

        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return appUser;
    }

    public AppUser add(String username, String password, String firstName, String lastName) {
        validate(username);
        validatePassword(password);

        password = encoder.encode(password);

        AppUser appUser = new AppUser(0, username, "", false, firstName, lastName, null, null, List.of());

        return repository.add(appUser);
    }

    public Result<AppUser> update(AppUser appUser) throws DataAccessException {
        Result<AppUser> result = validation(appUser);

        if (appUser.getAppUserId() <= 0) {
            result.addErrorMessage("User ID is required.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            if (repository.update(appUser)) {
                result.setPayload(appUser);
            } else {
                result.addErrorMessage("User ID %s was not found.", ResultType.NOT_FOUND, appUser.getAppUserId());
            }
        }
        return result;
    }


    public Result<AppUser> delete(int appUserId) throws DataAccessException {
        Result<AppUser> result = new Result<>();
        if (repository.delete(appUserId)) {
            result.addErrorMessage("User ID %s was not found.", ResultType.NOT_FOUND, appUserId);
        }
        return result;
    }

    private Result<AppUser> validation(AppUser appUser) {
        Result<AppUser> result = new Result<>();
        if (appUser.getUsername() == null || appUser.getUsername().isBlank()) {
            result.addErrorMessage("username is required",
                    ResultType.INVALID);
            return result;
        }

        if (appUser.getUsername().length() > 50) {
            result.addErrorMessage("username must be less than 50 characters",
                    ResultType.INVALID);
        }
        return result;
    }

    private void validate(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("username is required");
        }

        if (username.length() > 255) {
            throw new ValidationException("username must be less than 255 characters");
        }
    }


    private Result<AppUser> validatePassword(String password) {
        Result<AppUser> result = new Result<>();

        if (password == null || password.length() < 8) {
            result.addErrorMessage("password must be at least 8 characters",
                    ResultType.INVALID);
            return result;
        }

        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }

        if (digits == 0 || letters == 0 || others == 0) {
            result.addErrorMessage("password must contain a digit, a letter, and a non-digit/non-letter",
                    ResultType.INVALID);
        }

        return result;
    }

}
