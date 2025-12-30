package com.hsbc.CustomerAcctMgt.entity;

import com.hsbc.CustomerAcctMgt.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "transactions")
public class Transaction {
    //MOTT - moneyAmount, ownerAccount(join-relationship), typeTransaction, timestampCreatedAt

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void persist(){
        this.createdAt=LocalDateTime.now();
    }

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;
}
