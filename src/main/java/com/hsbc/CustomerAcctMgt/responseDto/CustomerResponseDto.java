package com.hsbc.CustomerAcctMgt.responseDto;

import java.time.LocalDateTime;
//- Contains only fields the server returns
//- No validation (because it's not a request)
//- Immutable and clean
public record CustomerResponseDto(
        Long id,
        String name,
        String email,
        String phone,
        LocalDateTime createdAt

) {}
