package com.example.javamicroservice.domain.mapper;

import com.example.javamicroservice.domain.Account;
import com.example.javamicroservice.dto.AccountDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountMapper {

    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);

}
