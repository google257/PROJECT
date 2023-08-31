package com.app.dataprovider;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.app.entities.Account;
import com.app.entities.Book;
import com.app.entities.User;
import com.app.repository.AccountRepository;
import com.app.repository.BookRepository;
import com.app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InitialData {

    private final BookRepository bookRepository;
    private final AccountRepository userRepository;
    private final UserRepository usrrepo;

    @Autowired
    public InitialData(BookRepository bookRepository, AccountRepository userRepository, UserRepository usrrepo) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.usrrepo = usrrepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addBooksToDB() {
        log.info("Persisted book data to database");
        bookRepository.save(
                new Book("The Blu Umbrella", "Ruskin Bond", "Fiction", LocalDateTime.now(), true));

        bookRepository.save(
                new Book("Delhi Is Not Far", "Ruskin Bond", "Fiction", LocalDateTime.now(), true));

        bookRepository.save(
                new Book("GOT", "George R.R. Martin", "Thriller", LocalDateTime.now(), true));

        bookRepository.save(
                new Book("Senbazaru", "Michael James Wong", "Drama", LocalDateTime.now(), true));
    }

//    @EventListener(ContextRefreshedEvent.class)
//    public void addUserToDB() {
//        log.info("Persisted account data to database");
//        userRepository.save(
//                new Account("admin", "123", 0));
//
//        userRepository.save(
//                new Account("Rjt", "123,", 0));
//    }
    
    //String userName, @NotNull String role, @NotNull String email, @NotNull String password,
	// @NotNull String mobileNumber
    @EventListener(ContextRefreshedEvent.class)
    public void addUserToDB() {
        log.info("Persisted account data to database");
        userRepository.save(
                new Account("admin", "123", 0));

        usrrepo .save(
                new User("Rjt", "user", "rjt@gmail.com", "09876", "9876543210"));
    }
}