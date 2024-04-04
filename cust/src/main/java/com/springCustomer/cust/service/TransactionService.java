package com.springCustomer.cust.service;

import com.springCustomer.cust.entity.Transaction;
import com.springCustomer.cust.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Invalid transaction data");
        }

        // Ensure that the orderid is set properly
        if (transaction.getOrder() == null || transaction.getOrder().getOrderid() == null) {
            throw new IllegalArgumentException("Order ID is required for creating a transaction");
        }

        // Save the transaction
        return transactionRepository.save(transaction);
    }

//    public Transaction createTransaction(Transaction transaction) {
//        if (transaction == null) {
//            throw new IllegalArgumentException("Invalid transaction data");
//        }
//        return transactionRepository.save(transaction);
//    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long transid) {
        return transactionRepository.findById(transid);
    }

    public Transaction updateTransaction(Long transid, Transaction updatedTransaction) {
        Optional<Transaction> existingTransactionOptional = transactionRepository.findById(transid);
        if (existingTransactionOptional.isPresent()) {
            Transaction existingTransaction = existingTransactionOptional.get();
            // Validate and update transaction attributes
            // Example: existingTransaction.setSomeAttribute(updatedTransaction.getSomeAttribute());
            return transactionRepository.save(existingTransaction);
        } else {
            throw new IllegalArgumentException("Transaction not found");
        }
    }

    public void deleteTransaction(Long transid) {
        transactionRepository.deleteById(transid);
    }

    public List<Transaction> getTransactionsByOrderId(Long orderid) {
        return transactionRepository.findByOrder_Orderid(orderid);
    }
}
