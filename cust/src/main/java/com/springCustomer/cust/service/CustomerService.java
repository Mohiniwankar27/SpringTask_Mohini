package com.springCustomer.cust.service;

import com.springCustomer.cust.entity.Customer;
import com.springCustomer.cust.entity.Order;
import com.springCustomer.cust.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    public Customer createCustomer(Customer customer) {
//        if (customer == null) {
//            throw new IllegalArgumentException("Invalid customer data");
//        }
//        return customerRepository.save(customer);
//    }

    public Customer createCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Invalid customer data");
        }

        // Iterate through the orders associated with the customer and set the customer for each order
        for (Order order : customer.getOrders()) {
            order.setCustomer(customer);
        }

        // Save the customer and associated orders
        return customerRepository.save(customer);
    }


    public List<Customer> createCustomers(List<Customer> customers) {
        // Optional input validation for customers list
        return customerRepository.saveAll(customers);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long custid) {
        return customerRepository.findById(custid);
    }

    public Customer updateCustomer(Long custid, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(custid);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setPhoneno(updatedCustomer.getPhoneno());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            return customerRepository.save(existingCustomer);
        } else {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    public boolean deleteCustomer(Long custid) {
        if (customerRepository.existsById(custid)) {
            customerRepository.deleteById(custid);
            return true;
        }
        return false;
    }
}



