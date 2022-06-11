package com.example.kiemtra.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class VsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus status;

    private String userMessage;

    private String devMessage;

    public VsException(){

    }

    public VsException(String devMessage)
    {
        super(devMessage);
        this.userMessage = devMessage;
    }

    public VsException(String userMessage, String devMessage)
    {
        super(userMessage);
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public VsException(String userMessage, String devMessage, HttpStatus status)
    {
        super(userMessage);
        this.userMessage = userMessage;
        this.devMessage = devMessage;
        this.status = status;
    }
}
