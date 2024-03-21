package com.example.demo.exception.handler;

import com.example.demo.exception.ErrorResponseBody;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorService errorService;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> noSuchElement(UserNotFoundException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> noSuchResumeElement(ResumeNotFoundException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }
}
