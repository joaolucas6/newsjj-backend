package com.joaolucas.newsjj.exceptions.handler;

import com.joaolucas.newsjj.exceptions.BadRequestException;
import com.joaolucas.newsjj.exceptions.ConflictException;
import com.joaolucas.newsjj.exceptions.ExceptionResponseBody;
import com.joaolucas.newsjj.exceptions.ResourceNotFoundException;
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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseBody> handleResourceNotFoundException(ResourceNotFoundException exception){
        String error = HttpStatus.NOT_FOUND.name();
        int errorCode = HttpStatus.NOT_FOUND.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponseBody> handleConflictException(ConflictException exception){
        String error = HttpStatus.CONFLICT.name();
        int errorCode = HttpStatus.CONFLICT.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponseBody> handleBadRequestException(BadRequestException exception){
        String error = HttpStatus.BAD_REQUEST.name();
        int errorCode = HttpStatus.BAD_REQUEST.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }

}
