package com.hsbc.CustomerAcctMgt.dtoForReference;

import com.hsbc.CustomerAcctMgt.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDtoLombok {
    //MOTT

    private Long id;
    private double amount;
    private TransactionType transactionType;
    private LocalDateTime createdAt;
    //private Account account;
    private Long accountId;
}
