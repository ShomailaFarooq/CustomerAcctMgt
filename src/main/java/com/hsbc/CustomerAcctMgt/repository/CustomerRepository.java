package com.hsbc.CustomerAcctMgt.repository;

import com.hsbc.CustomerAcctMgt.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
//    boolean existsByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email);
//
//    boolean existsByPhone(@NotBlank(message = "Phone number is required") @Pattern(
//                regexp = "^[0-9]{10,15}$",
//                message = "Phone number must be between 10 and 15 digits"
//        ) String phone);
//}
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
