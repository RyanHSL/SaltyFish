package com.saltyFish.appointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppointmentAlreadyExistsException extends RuntimeException {

    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }

}
