package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.InvalidObjectException;

@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * EmptyResultDataAccessException will be handled separately
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage InvalidIdException(Exception ex, WebRequest request) {
        return new ErrorMessage(1001, "Doi tuong khong ton tai");
    }

    // Handle Exception for add a new Post
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage InvalidIdAddException(Exception ex, WebRequest request) {
        return new ErrorMessage(1002, "Post khong dung dinh dang");
    }
}
