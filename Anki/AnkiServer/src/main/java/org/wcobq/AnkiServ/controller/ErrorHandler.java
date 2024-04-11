package org.wcobq.AnkiServ.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.wcobq.AnkiServ.service.IncorrectAwswerException;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({IncorrectAwswerException.class})
    private ResponseEntity<?> handle (final IncorrectAwswerException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
