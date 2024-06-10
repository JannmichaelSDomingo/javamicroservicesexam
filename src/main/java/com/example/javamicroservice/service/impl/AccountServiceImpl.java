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

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account create(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Optional<Account> get(Long customerNumber) {
        return accountRepo.findById(customerNumber);
    }

}
