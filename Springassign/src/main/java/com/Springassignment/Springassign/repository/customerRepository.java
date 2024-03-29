package com.Springassignment.Springassign.repository;
import com.Springassignment.Springassign.entity.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface customerRepository extends JpaRepository<customer, Integer>{
}
