package com.authorization.authorization.aop;

import com.authorization.authorization.exception.BadCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BadCredentialsException> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(ex, HttpStatus.FORBIDDEN);
    }
}


