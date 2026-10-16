package com.example.catalog_service_ch3.domain;

//import static org.junit.Assert.assertThat;      отличие               import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
//import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest; // <-- до надписи .jdbc.DataJdbcTest светится красным
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;// <-- также светится красным не воспринимаент import 
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;//+
//   org.springframework.boot.data.jdbc.test.autoconfigure
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.catalog_service_ch3.config.DataConfig;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest // светится красным и не предлагает импорт
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // это тоже светися красным и не предлагает
@ActiveProfiles("integration") // <-- что означает integration
public class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findAllBooks() { // <-- этот метод

        var book1 = Book.of("1234561235", "Title", "Author", 12.90);
        jdbcAggregateTemplate.insert(book1);
        Optional<Book> actualBook = bookRepository.findByIsbn("1234561235");

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book1.isbn());

    }

}
