package com.example.rest_datajpa.service;

import com.example.rest_datajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculateprice() {
        Book b1 = new Book(null,"cerditos","miguel",400,50.0, LocalDate.now(),true);
        BookPriceCalculator calculator = new BookPriceCalculator();
        Double price = calculator.calculateprice(b1);
        System.out.println( price);
        assertTrue(price > 0);
    }
}