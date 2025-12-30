package com.hsbc.CustomerAcctMgt.requestDto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//- Deposit requires accountId + amount.
//- Amount must be positive.
public record DepositRequest(
        @NotNull(message = "Account  Id is required")
        Long accountId,

        @Min(value = 1, message = "Amount must be greater than zero")
        double amount
) {
}
