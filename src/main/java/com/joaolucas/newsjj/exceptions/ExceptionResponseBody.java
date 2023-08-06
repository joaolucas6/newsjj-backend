package com.joaolucas.newsjj.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponseBody (
        String error, Integer errorCode, String message, LocalDateTime timestamp
){

}
