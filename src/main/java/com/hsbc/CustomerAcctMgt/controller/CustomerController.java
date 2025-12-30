package com.hsbc.CustomerAcctMgt.controller;

import com.hsbc.CustomerAcctMgt.requestDto.CreateCustomerRequest;
import com.hsbc.CustomerAcctMgt.responseDto.CustomerResponseDto;
import com.hsbc.CustomerAcctMgt.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/customers")
public class CustomerController {
    private final CustomerService customerService;

    //    CustomerResponseDto createCustomer(CreateCustomerRequest request);
    //    List<CustomerResponseDto> getAllCustomers();
    //    CustomerResponseDto getCustomerById(Long customerId);
    //    CustomerResponseDto updateCustomer(Long customerId, CreateCustomerRequest request);
    //    void deleteCustomer(Long customerId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponseDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public CustomerResponseDto getCustomerById(@PathVariable Long customerId){
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("/{customerId}")
    public CustomerResponseDto updateCustomer(@PathVariable Long customerId,@RequestBody CreateCustomerRequest request){
        return customerService.updateCustomer(customerId, request);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }


}
