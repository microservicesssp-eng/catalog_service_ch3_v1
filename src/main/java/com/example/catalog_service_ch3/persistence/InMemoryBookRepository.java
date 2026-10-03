package com.example.catalog_service_ch3.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.catalog_service_ch3.domain.Book;
import com.example.catalog_service_ch3.domain.BookRepository;


@Repository 
public class InMemoryBookRepository implements BookRepository  {


    private static final Map<String, Book> books = new ConcurrentHashMap<>();


    public Iterable<Book> findAll(){
        return books.values();
    }

    public Optional<Book> findByIsbn(String isbn){

        return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
    }

    public boolean existsByIsbn(String isbn){
        return books.get(isbn) != null;
    }

    public Book save(Book book){
        books.put(book.isbn(), book);

        return book;
    }

    public void deleteByIsbn(String isbn){
        books.remove(isbn);
    }
    
    
}
