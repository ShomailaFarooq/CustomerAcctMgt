package com.hsbc.CustomerAcctMgt.service;

import com.hsbc.CustomerAcctMgt.requestDto.CreateCustomerRequest;
import com.hsbc.CustomerAcctMgt.responseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto createCustomer(CreateCustomerRequest request);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(Long customerId);

    CustomerResponseDto updateCustomer(Long customerId, CreateCustomerRequest request);

    void deleteCustomer(Long customerId);
}
