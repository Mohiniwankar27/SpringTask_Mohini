package com.Springassignment.Springassign.repository;
import com.Springassignment.Springassign.entity.transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface transactionRepository  extends JpaRepository<transaction, Integer>{
}
