package learn.pawpals.controllers;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.domain.Result;
import learn.pawpals.domain.ResultType;
import learn.pawpals.domain.ScheduleService;
import learn.pawpals.models.Animal;
import learn.pawpals.models.AppUser;
import learn.pawpals.models.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animal/schedule")
public class ScheduleController {
    private final ScheduleService service;
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Schedule> findAll() throws DataAccessException {
        return service.findAll();
    }

    /*
    @GetMapping("/schedule/{dateTime}")
    public List<Schedule> findByDateTime(@PathVariable LocalDateTime dateTime) {
        return service.findByDateTime(dateTime);
    }
    */

    @GetMapping("/animalId/{animalId}")
    public List<Schedule> findByAnimal(@PathVariable int animalId) throws DataAccessException {
        return service.findByAnimal(animalId);
    }


    @GetMapping("/user")
    public List<Schedule> findByUser(UsernamePasswordAuthenticationToken principal) throws DataAccessException {
        AppUser appUser = (AppUser) principal.getPrincipal();
        return service.findAll()
                .stream().filter(sp -> sp.getAppUserId() == appUser.getAppUserId())
                .collect(Collectors.toList());
    }


    @GetMapping("/appUserId/{appUserId}")
    public List<Schedule> findByAdopter(@PathVariable int appUserId) throws DataAccessException {
        return service.findByAdopter(appUserId);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Schedule schedule) throws DataAccessException {
        Result<Schedule> result = service.add(schedule);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<?> update(@PathVariable int scheduleId, @RequestBody Schedule schedule) throws DataAccessException {
        if (scheduleId != schedule.getScheduleId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Schedule> result = service.update(schedule);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable int scheduleId) throws DataAccessException {
        Result<Schedule> result = service.delete((scheduleId));
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
