package com.example.javamicroservice.service.impl;

import com.example.javamicroservice.domain.Account;
import com.example.javamicroservice.repository.AccountRepo;
import com.example.javamicroservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Account create(Account account) {
        log.info("Creating new account for customer number: {}", account.getCustomerNumber());
        try {
            Account createdAccount = accountRepo.save(account);
            log.info("Account created successfully for customer number: {}", createdAccount.getCustomerNumber());
            return createdAccount;
        } catch (Exception e) {
            log.error("Failed to create account for customer number: {}", account.getCustomerNumber(), e);
            throw new RuntimeException("Account creation failed", e);
        }
    }

    @Override
    public Optional<Account> get(Long customerNumber) {
        log.info("Retrieving account for customer number: {}", customerNumber);
        Optional<Account> account = accountRepo.findById(customerNumber);
        if (account.isPresent()) {
            log.info("Account retrieved successfully for customer number: {}", customerNumber);
        } else {
            log.warn("No account found for customer number: {}", customerNumber);
        }
        return account;
    }
}
