package com.example.demo.service;

import com.example.demo.exception.ErrorResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


public interface ErrorService {

    ErrorResponseBody makeResponse(Exception exception);

    ErrorResponseBody makeResponse(BindingResult exception);

}
