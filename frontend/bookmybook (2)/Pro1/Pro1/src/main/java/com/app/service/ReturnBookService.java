package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.BookRentals;
import com.app.penalty.PenaltyService;
import com.app.repository.AccountRepository;
import com.app.repository.BookRentalsRepository;
import com.app.repository.BookRepository;

import java.time.LocalDateTime;

@Service
public class ReturnBookService {
    private final BookRentalsRepository bookRentalsRepository;
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;
    private final PenaltyService penaltyCheckerService;

    @Autowired
    public ReturnBookService(BookRentalsRepository bookRentalsRepository, BookRepository bookRepository, AccountRepository accountRepository, PenaltyService penaltyCheckerService) {
        this.bookRentalsRepository = bookRentalsRepository;
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
        this.penaltyCheckerService = penaltyCheckerService;
    }

    public String returnBook(int accountID, int bookID) {
        if (bookRentalsRepository.isBookRentedWithGivenIDByAccountWithGivenID(bookID, accountID)) {
            BookRentals bookToReturn = bookRentalsRepository.getBookBybBookID(bookID);
            accountRepository.findById(accountID).ifPresent(account -> {
                BookRentals preparedBookToReturn = prepareBookToReturn(bookToReturn);
                penaltyCheckerService.executePenaltyProcess(bookToReturn, account);
                updateBookAvailabilityAndSaveToDb(bookID);
                bookRentalsRepository.delete(preparedBookToReturn);
            });
        } else {
            throw new IllegalArgumentException("Book was not rented!");
        }
        return "Book was returned";
    }

    private BookRentals prepareBookToReturn(BookRentals preparedBookToReturn) {
        assert preparedBookToReturn != null;
        preparedBookToReturn.setDateOfReturn(LocalDateTime.now());
        return preparedBookToReturn;
    }

    private void updateBookAvailabilityAndSaveToDb(int bookID) {
        bookRepository.findById(bookID).ifPresent(book ->
        {
            book.setAvailable(true);
            bookRepository.save(book);
        });
    }
}