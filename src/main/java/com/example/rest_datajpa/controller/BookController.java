package com.example.rest_datajpa.controller;

import com.example.rest_datajpa.entities.Book;
import com.example.rest_datajpa.repository.BookRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    //atributos
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;
    //construtores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //getter y setter

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // CRUD sobre  la entidad Book

    // Buscar todos los libros
    @GetMapping("/api/Books")
    public List<Book> findAll(){
        // recuperar y devolver los libros de base de datos

        return bookRepository.findAll();
    }

    // Buscar todos los libros solo true
    @GetMapping("/api/Bookst")
    public List<Book> findAllt(){
        // recuperar y devolver los libros de base de datos que sean true
        return bookRepository.findByOnlineTrue();
    }


    // Buscar un solo libro en case de datos segun su id
    @GetMapping("/api/Bookst/{id}")
    public ResponseEntity<Book> findOneByID(@PathVariable Long id) {
        Optional<Book> bookopt = bookRepository.findById(id);
        if (bookopt.isPresent() && bookopt.get().getOnline()) {
            return ResponseEntity.ok(bookopt.get());
        } else {
            return ResponseEntity.notFound().build();

        }
        //return bookopt.orElse(null);
        //return bookRepository.findById(id).filter(book -> Boolean.TRUE.equals(book.getOnline())).orElse(null);
        // return ResponseEntity.of(bookopt);
        // return bookRepository.findById(id)
//        .filter(book -> Boolean.TRUE.equals(book.getOnline()))
//        .map(book -> ResponseEntity.ok(book))
//        .orElseGet(() -> ResponseEntity.notFound().build());
        //return bookopt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // crear un numero libro en base de datos
    @PostMapping("/api/bookst")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        if (book.getId() !=null){
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
    // actualizar un libro existente en base de datos
    @PutMapping("/api/bookst")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if (book.getId() == null){
            log.warn("Tryinq to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }


    // borrar un libro en base de datos
    @Hidden
    @DeleteMapping("/api/bookst/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if (!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    @Hidden
    @DeleteMapping("/api/bookst")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST Request for delete all bocks");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

