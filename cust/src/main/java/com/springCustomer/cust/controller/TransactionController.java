package com.springCustomer.cust.controller;

import com.springCustomer.cust.entity.Transaction;
import com.springCustomer.cust.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction createdTransaction = transactionService.createTransaction(transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{transid}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transid) {
        Optional<Transaction> transaction = transactionService.getTransactionById(transid);
        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{transid}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long transid, @RequestBody Transaction updatedTransaction) {
        try {
            Transaction transaction = transactionService.updateTransaction(transid, updatedTransaction);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{transid}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transid) {
        transactionService.deleteTransaction(transid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/{orderid}")
    public ResponseEntity<List<Transaction>> getTransactionsByOrderId(@PathVariable Long orderid) {
        List<Transaction> transactions = transactionService.getTransactionsByOrderId(orderid);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
