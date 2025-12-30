package com.hsbc.CustomerAcctMgt.service;

import com.hsbc.CustomerAcctMgt.entity.Customer;
import com.hsbc.CustomerAcctMgt.exception.ResourceNotFoundException;
import com.hsbc.CustomerAcctMgt.mapper.CustomerMapper;
import com.hsbc.CustomerAcctMgt.repository.CustomerRepository;
import com.hsbc.CustomerAcctMgt.requestDto.CreateCustomerRequest;
import com.hsbc.CustomerAcctMgt.responseDto.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto createCustomer(CreateCustomerRequest request) {
        Customer customerEntity=customerMapper.toEntity(request);
        Customer savedCustomer= customerRepository.save(customerEntity);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerResponseDto getCustomerById(Long customerId) {
        Customer customerEntity= customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
        return customerMapper.toDto(customerEntity);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long customerId, CreateCustomerRequest request) {
        //1.find customer
        Customer customerEntity= customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
        customerEntity.setName(request.name());
        customerEntity.setEmail(request.email());
        customerEntity.setPhone(request.phone());
        Customer updatedEntity =customerRepository.save(customerEntity);
        return customerMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customerEntity= customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
        customerRepository.delete(customerEntity);

    }
}
