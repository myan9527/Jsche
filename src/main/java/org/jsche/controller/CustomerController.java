package org.jsche.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;

import practice.entity.Customer;
import practice.repo.CustomerRepository;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository cp;

    @RequestMapping(value = "/get/{name}",method = RequestMethod.GET)
    public String getCustomer(@PathVariable("name")String firstName){
        List<Customer> customer = cp.findByFirstName(firstName);
        Gson gson = new Gson();
        if(customer != null && !customer.isEmpty()){
            return gson.toJson(customer);
        }
        return "No customer matched your search";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(Customer c){
        cp.save(c);
        return "success";
    }
}
