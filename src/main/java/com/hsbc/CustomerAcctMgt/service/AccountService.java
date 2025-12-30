package com.hsbc.CustomerAcctMgt.service;

import com.hsbc.CustomerAcctMgt.requestDto.CreateAccountRequest;
import com.hsbc.CustomerAcctMgt.requestDto.UpdateAccountRequest;
import com.hsbc.CustomerAcctMgt.responseDto.AccountResponseDto;

import java.util.List;

public interface AccountService {

    AccountResponseDto createAccount(CreateAccountRequest request);

    List<AccountResponseDto> getAllAccounts();

    AccountResponseDto getAccountById(Long accountId);

   // AccountResponseDto updateAccount(Long accountId, CreateAccountRequest request);

    AccountResponseDto updateAccount(Long accountId, UpdateAccountRequest request);

    List<AccountResponseDto> getAccountsByCustomerId(Long customerId);

    void deleteAccount(Long accountId);


}
