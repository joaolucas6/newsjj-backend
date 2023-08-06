package com.joaolucas.newsjj.exceptions.handler;

import com.joaolucas.newsjj.exceptions.ExceptionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseBody> handleGenericException(Exception exception){
        String error = HttpStatus.INTERNAL_SERVER_ERROR.name();
        int errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }

    
}
