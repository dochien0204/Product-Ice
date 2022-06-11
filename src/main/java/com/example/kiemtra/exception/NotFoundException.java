package com.example.kiemtra.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{

    private String userMessage;

    private String devMessage;

    private HttpStatus status;

    public NotFoundException(String devMessage)
    {
        super(devMessage);
        this.devMessage = devMessage;
    }

    public NotFoundException(String devMessage, String userMessage, HttpStatus status)
    {
        super(userMessage);
        this.devMessage = devMessage;
        this.userMessage = userMessage;
        this.status = status;
    }


}
