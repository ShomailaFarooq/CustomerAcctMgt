package com.hsbc.CustomerAcctMgt.mapper;

import com.hsbc.CustomerAcctMgt.entity.Account;
import com.hsbc.CustomerAcctMgt.entity.Customer;
import com.hsbc.CustomerAcctMgt.requestDto.CreateAccountRequest;
import com.hsbc.CustomerAcctMgt.requestDto.UpdateAccountRequest;
import com.hsbc.CustomerAcctMgt.responseDto.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

// CreateAccountRequest + Customer → Account entity
//Why two inputs?
//Because:
//- The request DTO only contains customerId
//- But the Account entity needs a full Customer object
//So the service layer does:
//Customer customer = customerRepository.findById(request.customerId());
//Account account = accountMapper.toEntity(request, customer);


//CreateAccountRequest (client input)
//        |
//        |  + Customer (loaded from DB)
//        v
//Account entity (ready to save)

@Mapper(componentModel = "spring")
public interface AccountMapper {

//    @Mapping(target = "customerId", source = "customer.id") //nested mapping --> customer id= account.getCustomer.getId
//    @Mapping(target = "accountType", source = "request.accountType")
//    @Mapping(target = "accountBalance", source = "request.initialBalance")
//    @Mapping(target = "accountStatus", source = "request.accountStatus")
//    Account toEntity(CreateAccountRequest request, Customer customer);
//
//    @Mapping(target = "customerId", source = "customer.id")
//    AccountResponseDto toDto(Account account);

    // Create Account: request + customer → entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "accountType", source = "request.accountType")
    @Mapping(target = "accountBalance", source = "request.initialBalance")
    @Mapping(target = "status", constant = "ACTIVE") // default status
    @Mapping(target = "nickname", ignore = true)     // optional field
    Account toEntity(CreateAccountRequest request, Customer customer);

    // Entity → Response DTO
    @Mapping(target = "customerId", source = "customer.id")
    AccountResponseDto toDto(Account account);

    // PATCH update: only update specific fields
    @Mapping(target = "status", source = "request.status")
    @Mapping(target = "nickname", source = "request.nickname")
    void updateEntityFromRequest(UpdateAccountRequest request, @MappingTarget Account account);



}
