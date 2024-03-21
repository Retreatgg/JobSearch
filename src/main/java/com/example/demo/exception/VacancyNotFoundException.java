package com.example.demo.exception;

public class VacancyNotFoundException extends Exception{
    public VacancyNotFoundException() {

    }

    public VacancyNotFoundException(String s) {
        super(s);
    }
}
