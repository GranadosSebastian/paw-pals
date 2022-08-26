package learn.pawpals.controllers;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.domain.Result;
import learn.pawpals.domain.ResultType;
import learn.pawpals.models.AppUser;
import learn.pawpals.domain.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/animal/appuser")
public class AppUserController {
    private final AppUserService service;
    public AppUserController(AppUserService service) {
        this.service = service;
    }


    @GetMapping
    public List<AppUser> findAll() throws DataAccessException {
        return service.findAll();
    }


    @PostMapping()
    public ResponseEntity<?> add(@RequestBody Map<String, String> body) throws DataAccessException {
        try {
            AppUser appUser = service.add(body.get("username"), body.get("password"), body.get("firstName"), body.get("lastName"));
            return new ResponseEntity<>(appUser, HttpStatus.CREATED); // 201
        } catch (ValidationException ex) {
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST); // 400
        }
    }

    @PutMapping("/{appUserId}")
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

    @DeleteMapping("/{appUserId}")
    public ResponseEntity<Void> delete(@PathVariable int appUserId) throws DataAccessException {
        Result<AppUser> result = service.delete((appUserId));
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
