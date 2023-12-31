package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Book;
import com.app.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookCrudOperationsService {

    private final BookRepository bookRepository;

    @Autowired
    public BookCrudOperationsService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book newBook) {
        newBook.setAvailable(true);
        newBook.setDateOfCreation(LocalDateTime.now());
        return bookRepository.save(newBook);
    }

    public Book deleteBook(int id) {
        Book bookToDelete = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book does not exist!"));
        bookRepository.deleteById(id);
        return bookToDelete;
    }

    public Book updateBook(Book updatedBook) {
        return bookRepository.save(updatedBook);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }
}