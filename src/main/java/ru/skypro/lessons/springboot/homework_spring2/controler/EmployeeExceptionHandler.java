package ru.skypro.lessons.springboot.homework_spring2.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleIOException(IOException ioException) {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
