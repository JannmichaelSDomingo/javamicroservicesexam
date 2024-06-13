package com.example.javamicroservice;

import com.example.javamicroservice.domain.Account;
import com.example.javamicroservice.domain.reference.AccountType;
import com.example.javamicroservice.repository.AccountRepo;
import com.example.javamicroservice.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void createAccount() {
        Account account = new Account();
        account.setCustomerMobile("0912345679");
        account.setCustomerNumber(1L);
        account.setCustomerName("Test");
        account.setCustomerEmail("email");
        account.setAddress1("add1");
        account.setAddress2("add2");
        account.setAccountType(AccountType.SAVINGS);
        when(accountRepo.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.create(account);

        assertNotNull(createdAccount);
        assertEquals(1L, createdAccount.getCustomerNumber());
        verify(accountRepo, times(1)).save(account);
    }

    @Test
    public void createAccountFailure() {
        Account account = new Account();
        account.setCustomerNumber(1L);
        when(accountRepo.save(any(Account.class))).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> accountService.create(account));

        assertEquals("Account creation failed", exception.getMessage());
        verify(accountRepo, times(1)).save(account);
    }

    @Test
    public void getAccountNotFound() {
        when(accountRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<Account> retrievedAccount = accountService.get(1L);

        assertFalse(retrievedAccount.isPresent());
        verify(accountRepo, times(1)).findById(1L);
    }
}
