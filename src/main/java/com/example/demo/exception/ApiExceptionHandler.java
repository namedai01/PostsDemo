package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    // EmptyResultDataAccessException will be handled separately
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage HandleEmptyResultDataAccessException(Exception ex, WebRequest request) {
        return new ErrorMessage(1001, "Object does not exist");
    }

    // Handle Exception for add a new Post
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage HandleInvalidDataAccessApiUsageException(Exception ex, WebRequest request) {
        return new ErrorMessage(1002, "Input is wrong format");
    }

    // Handle Exception for add a new Post
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage HandleInvalidFormatException(Exception ex, WebRequest request) {
        return new ErrorMessage(1003, "Id must not String");
    }


}
