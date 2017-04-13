package org.jsche.web.service.impl;

import org.jsche.entity.Customer;
import org.jsche.web.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/3/29.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public void save(Customer c) {

    }
}
