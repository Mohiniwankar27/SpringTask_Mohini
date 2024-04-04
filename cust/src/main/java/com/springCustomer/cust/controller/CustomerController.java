package com.springCustomer.cust.controller;

import com.springCustomer.cust.entity.Customer;
import com.springCustomer.cust.entity.Order;
import com.springCustomer.cust.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @PostMapping
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
//        Customer createdCustomer = customerService.createCustomer(customer);
//        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/batch")
//    public ResponseEntity<List<Customer>> createCustomers(@RequestBody List<Customer> customers) {
//        List<Customer> createdCustomers = customerService.createCustomers(customers);
//        return new ResponseEntity<>(createdCustomers, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            // Save the customer (this will make the customer managed)
            Customer createdCustomer = customerService.createCustomer(customer);

            // Now iterate through the orders associated with the customer and set the customer for each order
            for (Order order : createdCustomer.getOrders()) {
                order.setCustomer(createdCustomer);
            }

            // Save the updated customer with associated orders
            customerService.updateCustomer(createdCustomer.getCustid(), createdCustomer);

            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{custid}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long custid) {
        Optional<Customer> customer = customerService.getCustomerById(custid);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{custid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long custid, @RequestBody Customer updatedCustomer) {
        try {
            Customer customer = customerService.updateCustomer(custid, updatedCustomer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{custid}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long custid) {
        if (customerService.deleteCustomer(custid)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
