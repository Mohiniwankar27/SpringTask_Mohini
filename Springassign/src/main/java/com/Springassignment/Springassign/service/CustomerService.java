package com.Springassignment.Springassign.service;
import com.Springassignment.Springassign.entity.customer;
import com.Springassignment.Springassign.repository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final customerRepository customerRepo;

    @Autowired
    public CustomerService(customerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public customer createCustomer(customer customer) {
        return customerRepo.save(customer);
    }

    public customer getCustomerById(Integer custID) {
        Optional<customer> optionalCustomer = customerRepo.findById( custID);
        return optionalCustomer.orElse(null);
    }

    public List<customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public customer updateCustomer(customer customer) {
        return customerRepo.save(customer);
    }


    public void deleteCustomer(Integer custID) {
        customerRepo.deleteById( custID);
    }
}
