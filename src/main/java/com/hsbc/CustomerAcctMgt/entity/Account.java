package com.hsbc.CustomerAcctMgt.entity;

import com.hsbc.CustomerAcctMgt.enums.AccountStatus;
import com.hsbc.CustomerAcctMgt.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
//MOTT - moneyBalance ownerCustomer(join-relationship) type(enum)Account timestampCreatedAt

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double accountBalance=0.0;

    //should be enum not string
    //private String accountType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist()
    {
        this.createdAt=LocalDateTime.now();
    }

    //owner -- many accounts owned by one customer but one acct cannot have many customers owning it
    //this is te point where customer and account table join
    @ToString.Exclude //To avoid circular references in logs: prevents infinite recursion when printing objects
    @ManyToOne
    @JoinColumn(name= "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;   // NEW FIELD

    private String nickname;        // NEW FIELD




}
