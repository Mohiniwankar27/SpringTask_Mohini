package com.springCustomer.cust.service;

import com.springCustomer.cust.entity.Order;
import com.springCustomer.cust.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Invalid order data");
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderid) {
        return orderRepository.findById(orderid);
    }

    public Order updateOrder(Long orderid, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(orderid);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            // Validate and update order attributes
            // Example: existingOrder.setSomeAttribute(updatedOrder.getSomeAttribute());
            return orderRepository.save(existingOrder);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    public void deleteOrder(Long orderid) {
        orderRepository.deleteById(orderid);
    }

    public List<Order> getOrdersByCustomerId(Long custid) {
        return orderRepository.findByCustomerCustid(custid);
    }
}
