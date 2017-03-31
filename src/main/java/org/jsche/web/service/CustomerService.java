package org.jsche.web.service;

import java.util.List;

import org.jsche.entity.Customer;

public interface CustomerService {
    List<Customer> findByFirstName(String firstName);

    void save(Customer c);
}
