package com.springCustomer.cust.repository;

import com.springCustomer.cust.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerCustid(Long custid);
}
