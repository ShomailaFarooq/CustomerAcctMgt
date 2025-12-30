package com.hsbc.CustomerAcctMgt.responseDto;

import com.hsbc.CustomerAcctMgt.enums.TransactionType;

import java.time.LocalDateTime;
//- No entity exposure
//- No validation
//- Clean, immutable, API‑friendly
public record TransactionResponseDto(
        Long id,
        double amount,
        Long accountId,
        TransactionType transactionType,
        LocalDateTime createdAt

) {
}
