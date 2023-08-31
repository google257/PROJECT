package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.dto.BookIdDto;
import com.app.entities.Book;
import com.app.service.BookCrudOperationsService;

import java.util.List;

@RestController
@CrossOrigin(origins =  "http://localhost:3000")
public class BookCrudOperationsController {

    private final BookCrudOperationsService bookOperationsService;

    @Autowired
    public BookCrudOperationsController(BookCrudOperationsService bookOperationsService) {
        this.bookOperationsService = bookOperationsService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookOperationsService.getAllBooks();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book newBook) {
        return bookOperationsService.createBook(newBook);
    }

    @DeleteMapping("/books")
    public Book deleteBook(@RequestBody BookIdDto id) {
    	System.out.println(id.getBookId());
        return bookOperationsService.deleteBook(id.getBookId());
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book updatedBook) {
        return bookOperationsService.updateBook(updatedBook);
    }
}
