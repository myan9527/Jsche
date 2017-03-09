package org.jsche.service.impl;

import java.util.List;

import org.jsche.entity.Customer;
import org.jsche.repo.CustomerRepository;
import org.jsche.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository cp;

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return cp.findByFirstName(firstName);
    }

    @Override
    public void save(Customer c) {
        cp.save(c);
    }
    
    
}
