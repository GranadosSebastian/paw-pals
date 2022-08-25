package learn.pawpals.controllers;

import learn.pawpals.data.DataAccessException;
import learn.pawpals.domain.Result;
import learn.pawpals.domain.ResultType;
import learn.pawpals.domain.ScheduleService;
import learn.pawpals.models.Animal;
import learn.pawpals.models.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

//@RestController
//@RequestMapping
public class ScheduleController {
    /*
    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Schedule> findAll() throws DataAccessException {
        return service.findAll();
    }
    @GetMapping("/schedule/{dateTime}")
    public List<Schedule> findByDateTime(@PathVariable LocalDateTime dateTime) {
        return service.findByDateTime(dateTime);
    }

    @GetMapping("/schedule/{animal}")
    public List<Schedule> findByAnimal(@PathVariable Animal animal) throws DataAccessException {
        return service.findByAnimal(animal);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Schedule schedule) throws DataAccessException {
        Result<Schedule> result = service.add(schedule);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }


    @PutMapping("/schedule/{scheduleId}")
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

    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable int scheduleId) throws DataAccessException {
        Result<Schedule> result = service.delete((scheduleId));
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    */

}
