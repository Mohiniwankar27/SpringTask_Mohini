package com.springCustomer.cust.repository;

import com.springCustomer.cust.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
