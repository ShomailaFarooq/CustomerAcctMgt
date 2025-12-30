package com.hsbc.CustomerAcctMgt.service;

import com.hsbc.CustomerAcctMgt.responseDto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    TransactionResponseDto deposit(Long accountId, double amount);

    TransactionResponseDto withdraw(Long accountId, double amount);

    TransactionResponseDto transfer(Long fromAccountId, Long toAccountId, double amount);

    List<TransactionResponseDto> getTransactionsByAccountId(Long accountId);
}

