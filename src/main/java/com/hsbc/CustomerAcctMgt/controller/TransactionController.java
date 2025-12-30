package com.hsbc.CustomerAcctMgt.controller;

import com.hsbc.CustomerAcctMgt.responseDto.TransactionResponseDto;
import com.hsbc.CustomerAcctMgt.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto deposit(
            @RequestParam Long accountId,
            @RequestParam double amount
    ) {
        return transactionService.deposit(accountId, amount);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto withdraw(
            @RequestParam Long accountId,
            @RequestParam double amount
    ) {
        return transactionService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto transfer(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam double amount
    ) {
        return transactionService.transfer(fromAccountId, toAccountId, amount);
    }
    @GetMapping("/account/{accountId}")
    public List<TransactionResponseDto> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
