package com.example.catalog_service_ch3.domain;



public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn){
        super("The book with ISBN "+isbn+ " was not found.");
    }
    
}
