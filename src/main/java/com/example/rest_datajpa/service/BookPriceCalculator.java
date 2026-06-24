package com.example.rest_datajpa.service;


import com.example.rest_datajpa.entities.Book;

public class BookPriceCalculator {
    public Double calculateprice(Book book){
        Double price = book.getPrice();

        if (book.getPages()>300){
            price +=5;
        }
        return price;
    }


}
