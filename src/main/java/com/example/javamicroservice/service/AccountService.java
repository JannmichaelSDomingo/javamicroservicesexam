package com.example.javamicroservice.service;

import com.example.javamicroservice.domain.Account;

import java.util.Optional;

public interface AccountService {

    Account create(Account account);

    Optional<Account> get(Long customerNumber);

}
