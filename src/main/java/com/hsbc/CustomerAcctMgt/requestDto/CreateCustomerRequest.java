package com.hsbc.CustomerAcctMgt.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
//Used when creating a customer
//✔ Contains validation
//✔ No id or createdAt (system-generated)
public record CreateCustomerRequest(
        @NotBlank(message = "Customer name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[0-9]{10,15}$",
                message = "Phone number must be between 10 and 15 digits"
        )
        String phone
) {
}
