package com.hsbc.CustomerAcctMgt.repository;

import com.hsbc.CustomerAcctMgt.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByCustomerId(Long customerId);

}
