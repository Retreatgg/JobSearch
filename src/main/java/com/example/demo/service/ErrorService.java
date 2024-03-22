package com.example.demo.service;

import com.example.demo.exception.ErrorResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ErrorService {

    ErrorResponseBody makeResponse(Exception exception);

    ErrorResponseBody makeResponse(BindingResult exception);

}
