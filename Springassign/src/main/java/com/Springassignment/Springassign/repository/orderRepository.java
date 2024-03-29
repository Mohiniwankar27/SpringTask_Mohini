package com.Springassignment.Springassign.repository;
import com.Springassignment.Springassign.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface orderRepository extends JpaRepository<order, Integer>{
}
