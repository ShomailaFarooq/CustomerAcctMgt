package com.hsbc.CustomerAcctMgt.service;

import com.hsbc.CustomerAcctMgt.entity.Customer;
import com.hsbc.CustomerAcctMgt.exception.DuplicateEmailException;
import com.hsbc.CustomerAcctMgt.exception.DuplicatePhoneException;
import com.hsbc.CustomerAcctMgt.exception.ResourceNotFoundException;
import com.hsbc.CustomerAcctMgt.mapper.CustomerMapper;
import com.hsbc.CustomerAcctMgt.repository.CustomerRepository;
import com.hsbc.CustomerAcctMgt.requestDto.CreateCustomerRequest;
import com.hsbc.CustomerAcctMgt.responseDto.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Override
    @Transactional
    public CustomerResponseDto createCustomer(CreateCustomerRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException("Email already exists");
        }

        if (customerRepository.existsByPhone(request.phone())) {
            throw new DuplicatePhoneException("Phone number already exists");
        }
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

    @Cacheable(value = "customers", key = "#id")
    @Override
    public CustomerResponseDto getCustomerById(Long customerId) {
        log.info("Fetching customer {} from DB", customerId);
        Customer customerEntity= customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
        return customerMapper.toDto(customerEntity);
    }

    @CachePut
    @Override
    @Transactional
    public CustomerResponseDto updateCustomer(Long customerId, CreateCustomerRequest request) {
        //1.find customer
        Customer customerEntity= customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
        // Email uniqueness check
        if (!customerEntity.getEmail().equals(request.email()) &&
                customerRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException("Email already exists");
        }

        // Phone uniqueness check
        if (!customerEntity.getPhone().equals(request.phone()) &&
                customerRepository.existsByPhone(request.phone())) {
            throw new DuplicatePhoneException("Phone number already exists");
        }

        customerEntity.setName(request.name());
        customerEntity.setEmail(request.email());
        customerEntity.setPhone(request.phone());
        Customer updatedEntity =customerRepository.save(customerEntity);
        return customerMapper.toDto(updatedEntity);
    }

    @CacheEvict
    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customerEntity= customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
        customerRepository.delete(customerEntity);

    }
}
