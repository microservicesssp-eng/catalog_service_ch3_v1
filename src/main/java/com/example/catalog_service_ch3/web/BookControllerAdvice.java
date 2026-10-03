package com.example.catalog_service_ch3.web;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.example.catalog_service_ch3.domain.BookAlreadyExistsException;
import com.example.catalog_service_ch3.domain.BookNotFoundException;


@RestControllerAdvice 
public class BookControllerAdvice {



    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String bookAlreadyExistsHandler(BookAlreadyExistsException ex){

        return ex.getMessage();

    }

     @ExceptionHandler (MethodArgumentNotValidException.class)
     @ResponseStatus (HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){//<-- это собственное исключение Java ?
        var errors = new HashMap<String, String>(); 

        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
}

// Когда возникает MethodArgumentNotValidException

// Spring выбрасывает его сам, когда не проходит проверка параметра, помеченного @Valid:

// java
// @PostMapping
// public Book post(@Valid @RequestBody Book book) { ... }
