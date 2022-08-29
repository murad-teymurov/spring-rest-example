package com.company.YearSpringRestApi.exception;

public class CityAlreadyExistsException extends RuntimeException{

    public CityAlreadyExistsException(String message){
        super(message);
    }
}
