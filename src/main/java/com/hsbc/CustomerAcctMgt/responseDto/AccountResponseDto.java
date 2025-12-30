package com.hsbc.CustomerAcctMgt.responseDto;

import com.hsbc.CustomerAcctMgt.enums.AccountStatus;
import com.hsbc.CustomerAcctMgt.enums.AccountType;

import java.time.LocalDateTime;
//- customerId instead of Customer entity
//- No validation
//- Perfect for API responses
public record AccountResponseDto(
        Long id,
        Long customerId,
        AccountType accountType,
        double accountBalance,
        AccountStatus status, //new
        String nickname,//new
        LocalDateTime createdAt

) {}
