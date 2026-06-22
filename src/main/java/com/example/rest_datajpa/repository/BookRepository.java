package com.example.rest_datajpa.repository;

import com.example.rest_datajpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByOnlineTrue();
}
