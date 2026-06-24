package com.example.rest_datajpa.controller;


import com.example.rest_datajpa.entities.Book;
import com.example.rest_datajpa.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class BookControllerTest {

    @Autowired
    private RestTestClient client;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @DisplayName("comprobando controladores de rest de Spring rest")
    @Test
    void findAll() {
        Book savedBook = bookRepository.save(new Book(null, "Spring Boot", "Miguel", 200, 45.0, LocalDate.now(), true));

        client.get()
                .uri("/api/Books")
                .exchange()
                .expectStatus().isOk();

        assertEquals(1, bookRepository.count());
        assertNotNull(savedBook.getId());
        assertEquals("Spring Boot", savedBook.getTitle());
    }

    @Test
    void findOneById() {
        Book saved = bookRepository.save(new Book(null, "Spring Boot", "Miguel", 200, 45.0, LocalDate.now(), true));

        client.get()
                .uri("/api/Bookst/{id}", saved.getId())
                .exchange()
                .expectStatus().isOk();

        assertNotNull(saved.getId());
        assertEquals(true, saved.getOnline());
        assertEquals(1, bookRepository.count());
    }
    
    @Test
    void create() {
        Book book = new Book(null, "Spring Boot", "Miguel", 400, 50.0, LocalDate.now(), true);

        client.post()
                .uri("/api/bookst")
                .body(book)
                .exchange()
                .expectStatus().isOk();

        assertEquals(1, bookRepository.count());
        Book storedBook = bookRepository.findAll().get(0);
        assertNotNull(storedBook.getId());
        assertEquals("Spring Boot", storedBook.getTitle());
        assertFalse(bookRepository.findByOnlineTrue().isEmpty());
    }

}