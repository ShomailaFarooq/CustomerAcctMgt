package com.hsbc.CustomerAcctMgt.mapper;

import com.hsbc.CustomerAcctMgt.entity.Transaction;
import com.hsbc.CustomerAcctMgt.responseDto.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    //- Extracts the nested account.id into a flat accountId
    @Mapping(target = "accountId", source = "account.id")
    TransactionResponseDto toDto(Transaction transaction);
}

