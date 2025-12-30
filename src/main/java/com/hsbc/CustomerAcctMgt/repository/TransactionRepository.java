package com.hsbc.CustomerAcctMgt.repository;

import com.hsbc.CustomerAcctMgt.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
