package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResumeNotFoundException extends RuntimeException {
    public ResumeNotFoundException() {
        super();
    }

}
