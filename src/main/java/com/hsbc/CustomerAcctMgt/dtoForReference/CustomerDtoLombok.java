package com.hsbc.CustomerAcctMgt.dtoForReference;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoLombok {
    //NEPT
    //Validations

    private Long id;

    @NotBlank(message= "Customer name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message= "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10,15}$",
            message = "Phone number must be between 10 and 15 digits"
    )
    private String phone;

    private LocalDateTime createdAt;
}
