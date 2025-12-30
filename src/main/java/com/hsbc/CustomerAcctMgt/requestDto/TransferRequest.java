package com.hsbc.CustomerAcctMgt.requestDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//- Both accounts must be provided.
//- Amount must be positive.
//- Validation prevents invalid transfers before hitting service logic.
public record TransferRequest(
        @NotNull(message = "Source account ID is required")
        Long fromAccountId,

        @NotNull(message = "Destination account ID is required")
        Long toAccountId,

        @Min(value = 1, message = "Amount must be greater than zero")
        double amount

) {
}
