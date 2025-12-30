package com.hsbc.CustomerAcctMgt.controller;


import com.hsbc.CustomerAcctMgt.requestDto.CreateAccountRequest;
import com.hsbc.CustomerAcctMgt.requestDto.UpdateAccountRequest;
import com.hsbc.CustomerAcctMgt.responseDto.AccountResponseDto;
import com.hsbc.CustomerAcctMgt.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto createAccount(@RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping("/{accountId}")
    public AccountResponseDto getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountResponseDto> getAccountsByCustomerId(@PathVariable Long customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }

    @GetMapping
    public List<AccountResponseDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PatchMapping("/{accountId}")
    public AccountResponseDto updateAccount(
            @PathVariable Long accountId,
            @RequestBody UpdateAccountRequest request
    ) {
        return accountService.updateAccount(accountId, request);
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
    }



}
