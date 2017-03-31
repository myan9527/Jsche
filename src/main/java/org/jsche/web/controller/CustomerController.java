package org.jsche.web.controller;

import java.util.List;

import org.jsche.entity.Customer;
import org.jsche.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService cs;

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public String getCustomer(@PathVariable("name") String firstName) {
        List<Customer> customer = cs.findByFirstName(firstName);
        Gson gson = new Gson();
        if (customer != null && !customer.isEmpty()) {
            return gson.toJson(customer);
        }
        return "No customer matched your search";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(Customer c) {
        cs.save(c);
        return "success";
    }
}
