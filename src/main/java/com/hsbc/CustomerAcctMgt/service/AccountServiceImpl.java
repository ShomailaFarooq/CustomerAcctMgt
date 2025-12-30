package com.hsbc.CustomerAcctMgt.service;

import com.hsbc.CustomerAcctMgt.entity.Account;
import com.hsbc.CustomerAcctMgt.entity.Customer;
import com.hsbc.CustomerAcctMgt.enums.AccountStatus;
import com.hsbc.CustomerAcctMgt.exception.ResourceNotFoundException;
import com.hsbc.CustomerAcctMgt.mapper.AccountMapper;
import com.hsbc.CustomerAcctMgt.repository.AccountRepository;
import com.hsbc.CustomerAcctMgt.repository.CustomerRepository;
import com.hsbc.CustomerAcctMgt.requestDto.CreateAccountRequest;
import com.hsbc.CustomerAcctMgt.requestDto.UpdateAccountRequest;
import com.hsbc.CustomerAcctMgt.responseDto.AccountResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public AccountResponseDto createAccount(CreateAccountRequest request) {
        // 1. Validate customer exists
        Customer customer= customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        // 2. Convert request + customer → Account entity
        Account account= accountMapper.toEntity(request,customer);
        // 3. Set metadata
        account.setCreatedAt(LocalDateTime.now());
        account.setStatus(AccountStatus.ACTIVE);
        // 4. Save
        System.out.println("DEBUG: account.id before save = " + account.getId());
        Account savedAccount = accountRepository.save(account);
        // 5. Convert to response DTO
        return accountMapper.toDto(savedAccount);
    }

    @Override
    public List<AccountResponseDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public AccountResponseDto getAccountById(Long accountId) {
        Account account= accountRepository.findById(accountId)
                .orElseThrow(()->new ResourceNotFoundException("Account not found"));
        return accountMapper.toDto(account);
    }

    @Override
    @Transactional
    public AccountResponseDto updateAccount(Long accountId, UpdateAccountRequest request) {
        // 1. Fetch existing account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // 2. Apply updates using MapStruct
        accountMapper.updateEntityFromRequest(request, account);

        // 3. Save updated entity
        System.out.println("DEBUG: account.id before save = " + account.getId());
        Account updated = accountRepository.save(account);

        // 4. Convert to response DTO
        return accountMapper.toDto(updated);
    }

    @Override
    public List<AccountResponseDto> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {
        Account account= accountRepository.findById(accountId)
                .orElseThrow(()->new ResourceNotFoundException("Account not found"));
        accountRepository.delete(account);

    }

}
