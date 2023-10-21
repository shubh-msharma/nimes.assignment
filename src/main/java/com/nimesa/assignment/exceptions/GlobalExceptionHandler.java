package com.nimesa.assignment.exceptions;

import com.nimesa.assignment.models.dtos.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

    @ExceptionHandler(MissingRequiredParamException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequiredParamException(MissingRequiredParamException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }

    @ExceptionHandler(ActionNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleActionNotAllowedException(ActionNotAllowedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }
}
