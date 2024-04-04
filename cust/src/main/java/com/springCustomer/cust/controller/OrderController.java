package com.springCustomer.cust.controller;

import com.springCustomer.cust.entity.Order;
import com.springCustomer.cust.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderid) {
        Optional<Order> order = orderService.getOrderById(orderid);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{orderid}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderid, @RequestBody Order updatedOrder) {
        try {
            Order order = orderService.updateOrder(orderid, updatedOrder);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderid}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderid) {
        orderService.deleteOrder(orderid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer/{custid}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long custid) {
        List<Order> orders = orderService.getOrdersByCustomerId(custid);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
