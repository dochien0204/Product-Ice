package com.example.kiemtra.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRespone handleNotFoundException(NotFoundException ex, WebRequest rq)
    {
        return new ErrorRespone(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespone handleDuplicateException(DuplicateException exception, WebRequest rq)
    {
        return new ErrorRespone(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(UploadFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespone handleUploadFileException(UploadFileException ex, WebRequest req) {
        return new ErrorRespone(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
