package com.hsbc.CustomerAcctMgt.requestDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//- Same rules as deposit.
//- Validation ensures no negative or zero withdrawals.
public record WithdrawRequest(
        @NotNull(message = "Account ID is required")
        Long accountId,

        @Min(value = 1, message = "Amount must be greater than zero")
        double amount
) {
}
