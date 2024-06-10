package com.example.javamicroservice.resource;

import com.example.javamicroservice.domain.Account;
import com.example.javamicroservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.create(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<Object> getAccountDetails(@PathVariable Long customerNumber) {
        Optional<Account> account = accountService.get(customerNumber);
        if (account.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(account + "{\"transactionStatusCode\":302,\"transactionStatusDescription\":\"Customer Account found\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"transactionStatusCode\":401,\"transactionStatusDescription\":\"Customer not found\"}");
        }
    }
}
