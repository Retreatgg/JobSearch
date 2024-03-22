package com.example.demo.exception.handler;

import com.example.demo.exception.ErrorResponseBody;
import com.example.demo.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorService errorService;
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseBody> noSuchElement(NoSuchElementException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseBody> illegalArgumentHandler(IllegalArgumentException e) {
        return new ResponseEntity<>(errorService.makeResponse(e), HttpStatus.BAD_REQUEST);
    }
}
