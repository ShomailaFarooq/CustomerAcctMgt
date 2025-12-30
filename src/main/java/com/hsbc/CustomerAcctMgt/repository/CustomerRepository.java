package com.hsbc.CustomerAcctMgt.repository;

import com.hsbc.CustomerAcctMgt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
}
