package com.Springassignment.Springassign.service;

import com.Springassignment.Springassign.entity.order;
import com.Springassignment.Springassign.repository.orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final orderRepository orderRepo;

    @Autowired
    public OrderService(orderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public order createOrder(order order) {
        return orderRepo.save(order);
    }

    public order getOrderById(Integer orderID) {
        Optional<order> optionalOrder = orderRepo.findById(orderID);
        return optionalOrder.orElse(null);
    }

    public List<order> getAllOrders() {
        return orderRepo.findAll();
    }

    public order updateOrder(order order) {
        return orderRepo.save(order);
    }

    public void deleteOrder(Integer orderID) {
        orderRepo.deleteById(orderID);
    }
}
