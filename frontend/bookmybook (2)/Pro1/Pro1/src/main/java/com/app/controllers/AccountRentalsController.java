package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.BookRentals;
import com.app.service.AccountRentalsService;

import java.util.List;

@RestController
public class AccountRentalsController {

    private final AccountRentalsService accountRentalsService;

    @Autowired
    public AccountRentalsController(AccountRentalsService accountRentalsService) {
        this.accountRentalsService = accountRentalsService;
    }

    @GetMapping("books/rentals/{accountID}")
    public List<BookRentals> findAccountRentalsByGivenID(@PathVariable int accountID) {
        return accountRentalsService.findAccountRentalsByGivenID(accountID);
    }
}