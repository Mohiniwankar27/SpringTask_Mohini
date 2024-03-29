package com.Springassignment.Springassign.service;
import com.Springassignment.Springassign.entity.transaction;
import com.Springassignment.Springassign.repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final transactionRepository transactionRepo;

    @Autowired
    public TransactionService(transactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public transaction createTransaction(transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public transaction getTransactionById(Integer ID) {
        Optional<transaction> optionalTransaction = transactionRepo.findById(ID);
        return optionalTransaction.orElse(null);
    }

    public List<transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public transaction updateTransaction(transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(Integer ID) {
        transactionRepo.deleteById(ID);
    }
}
