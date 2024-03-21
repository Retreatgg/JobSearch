package com.example.demo.service.impl;

import com.example.demo.exception.ErrorResponseBody;
import com.example.demo.service.ErrorService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorServiceImpl implements ErrorService {
    @Override
    public ErrorResponseBody makeResponse(Exception exception) {
        String message = exception.getMessage();

        return ErrorResponseBody.builder()
                .title(message)
                .reasons(List.of(message))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult exception) {
        List<String> errors = new ArrayList<>();

        for (ObjectError error : exception.getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return ErrorResponseBody.builder()
                .title("Validation error")
                .reasons(errors)
                .build();
    }
}
