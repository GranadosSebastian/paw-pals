package learn.pawpals.controllers;

import learn.pawpals.data.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // log this exception

        return new ResponseEntity<>(
                new ErrorResponse("Sorry, something unexpected went wrong."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

