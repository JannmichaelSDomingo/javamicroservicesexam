package com.example.javamicroservice.dto;

import com.example.javamicroservice.domain.reference.AccountType;
import lombok.Data;

@Data
public class AccountDto {

    private Long customerNumber;

    private String customerName;

    private String customerMobile;

    private String customerEmail;

    private String address1;

    private String address2;

    private AccountType accountType;
}
