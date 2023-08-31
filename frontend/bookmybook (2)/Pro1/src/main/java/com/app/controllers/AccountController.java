package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Account;
import com.app.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService userService) {
        this.accountService = userService;
    }

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account newAccount) {
        return accountService.createAccount(newAccount);
    }

    @DeleteMapping("/account")
    public Account deleteAccount(@RequestParam int id) {
        return accountService.deleteAccount(id);
    }

    @GetMapping("/account")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
