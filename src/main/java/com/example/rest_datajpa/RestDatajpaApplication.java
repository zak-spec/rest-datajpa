package com.example.rest_datajpa;

import com.example.rest_datajpa.entities.Book;
import com.example.rest_datajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class RestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(RestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		//crud

		//crear libro
		Book book1 = new Book(null,"niños ingratos ","sara",10,10.36, LocalDate.of(2020,12,1),true);
		Book book2 = new Book(null,"chicomalo","jesus");


		//almacenar un libro
		System.out.println("num libros" + repository.findAll().size());
		repository.save(book1);
		repository.save(book2);

		//recuperar todos los libros
		System.out.println("num libros" + repository.findAll().size());
		System.out.println(repository.findAll());

		//borar un libro
		//repository.deleteById(1L)
		System.out.println("num libros" + repository.findAll().size());
		System.out.println(repository.findAll());




	}

}
