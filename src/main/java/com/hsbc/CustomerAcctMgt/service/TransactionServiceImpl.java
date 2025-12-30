package com.hsbc.CustomerAcctMgt.service;

import com.hsbc.CustomerAcctMgt.entity.Account;
import com.hsbc.CustomerAcctMgt.entity.Transaction;
import com.hsbc.CustomerAcctMgt.enums.TransactionType;
import com.hsbc.CustomerAcctMgt.exception.ResourceNotFoundException;
import com.hsbc.CustomerAcctMgt.mapper.TransactionMapper;
import com.hsbc.CustomerAcctMgt.repository.AccountRepository;
import com.hsbc.CustomerAcctMgt.repository.TransactionRepository;
import com.hsbc.CustomerAcctMgt.responseDto.TransactionResponseDto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public TransactionResponseDto deposit(Long accountId, double amount) {
        //check if amount is positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        //Find account by id
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // Update balance --> existing balance + amount
        account.setAccountBalance(account.getAccountBalance() + amount);
        accountRepository.save(account);

        // Create transaction record
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .transactionType(TransactionType.DEPOSIT)
                .createdAt(LocalDateTime.now())
                .build();

        //save transaction record
        Transaction saved = transactionRepository.save(transaction);

        return transactionMapper.toDto(saved);
    }

    @Override
    @Transactional
    public TransactionResponseDto withdraw(Long accountId, double amount) {
        if(amount<=0){
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }
        //Find account by id
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getAccountBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        //Update Balance in account
        account.setAccountBalance(account.getAccountBalance()-amount);
        accountRepository.save(account);

        //Create transaction record
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .transactionType(TransactionType.WITHDRAW)
                .createdAt(LocalDateTime.now())
                .build();

        //save transaction
        Transaction saved=transactionRepository.save(transaction);
        return transactionMapper.toDto(saved);
    }


    @Override
    @Transactional
    public TransactionResponseDto transfer(Long fromAccountId, Long toAccountId, double amount) {
        if(amount<=0){
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }
        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Destination account not found"));
        if (fromAccount.getAccountBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for transfer");
        }
        // Withdraw from source
        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
        accountRepository.save(fromAccount);
        // Deposit into destination
        toAccount.setAccountBalance(toAccount.getAccountBalance() + amount);
        accountRepository.save(toAccount);
        // Create transaction record (for source)
        Transaction transaction = Transaction.builder()
                .account(fromAccount)
                .amount(amount)
                .transactionType(TransactionType.TRANSFER)
                .createdAt(LocalDateTime.now())
                .build();

        Transaction saved = transactionRepository.save(transaction);

        return transactionMapper.toDto(saved);
    }

    @Override
    public List<TransactionResponseDto> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }
}
