package com.hsbc.CustomerAcctMgt.mapper;

import com.hsbc.CustomerAcctMgt.entity.Customer;
import com.hsbc.CustomerAcctMgt.requestDto.CreateCustomerRequest;
import com.hsbc.CustomerAcctMgt.responseDto.CustomerResponseDto;
import org.mapstruct.Mapper;

//withMapStruct
@Mapper(componentModel="spring")
public interface CustomerMapper {
    Customer toEntity(CreateCustomerRequest request);

    CustomerResponseDto toDto(Customer customer);

}
