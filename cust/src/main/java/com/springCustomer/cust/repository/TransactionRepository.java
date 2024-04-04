package com.springCustomer.cust.repository;
import com.springCustomer.cust.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByOrderOrderid(Long orderid);

    List<Transaction> findByOrder_Orderid(Long orderid);
    // You can define custom query methods here if needed
}
