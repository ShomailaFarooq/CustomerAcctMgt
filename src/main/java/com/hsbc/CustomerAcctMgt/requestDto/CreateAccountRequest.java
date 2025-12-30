package com.hsbc.CustomerAcctMgt.requestDto;

import com.hsbc.CustomerAcctMgt.enums.AccountType;
import jakarta.validation.constraints.*;

public record CreateAccountRequest(

        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotNull(message = "Account type is required")
        AccountType accountType,

        @Min(value = 0, message = "Initial balance cannot be negative")
        double initialBalance

) {
}
