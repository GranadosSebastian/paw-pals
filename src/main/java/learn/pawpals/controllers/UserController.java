package learn.pawpals.controllers;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.domain.Result;
import learn.pawpals.domain.ResultType;
import learn.pawpals.domain.UserService;
import learn.pawpals.models.Animal;
import learn.pawpals.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppUser> findAll() throws DataAccessException {
        return service.findAll();
    }

    @GetMapping("/{roleId}")
    public List<AppUser> findByRole(@PathVariable int roleId) throws DataAccessException {
        return service.findByRole(roleId);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AppUser appUser) throws DataAccessException {
        Result<AppUser> result = service.add(appUser);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable int appUserId, @RequestBody AppUser appUser) throws DataAccessException {
        if (appUserId != appUser.getAppUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<AppUser> result = service.update(appUser);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(int userId) throws DataAccessException {
        Result<AppUser> result = service.delete((userId));
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
