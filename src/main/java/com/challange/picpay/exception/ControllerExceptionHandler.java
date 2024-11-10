package com.challange.picpay.exception;

import com.challange.picpay.model.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO ex = new ExceptionDTO("User already exist", HttpStatus.BAD_REQUEST, "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity generalException(Exception exception){
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, "422");
        return ResponseEntity.unprocessableEntity().body(ex);
    }

}
