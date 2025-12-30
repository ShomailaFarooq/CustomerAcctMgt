package com.hsbc.CustomerAcctMgt.dtoForReference;


import com.hsbc.CustomerAcctMgt.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDtoLombok {
    //MOTT

    private Long id;

    private Long customerId;

    private AccountType accountType;

    private double accountBalance;

    private LocalDateTime createdAt;

}
