/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.jsche.entity.Customer;
import org.jsche.service.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


/**
 * Sample code for rest controller test case.
 * @author myan
 */
@RunWith(SpringRunner.class)
public class CustomerControllerTest extends WebAPIBaseTest<CustomerController>{
    @Mock
    private CustomerService cs;
    @InjectMocks
    private CustomerController controller;
    Customer customer;
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }
    
    @After
    public void tearDown() {
        //cp.delete(c);
    }

    /**
     * Test of getByFirstName method, of class CustomerController.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetByFirstName() throws Exception {        
        RequestBuilder request = null;
        customer = new Customer("michael", "yan");
        List<Customer> cuss = new ArrayList<>();
        cuss.add(customer);
        //mock first
        Mockito.when(cs.findByFirstName("michael")).thenReturn(cuss);
        
        //get it
        request = MockMvcRequestBuilders.get("/get/michael");
        perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"firstName\":\"michael\",\"lastName\":\"yan\"}]"));
        //then get another
        request = MockMvcRequestBuilders.get("/get/none");
        perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("No customer matched your search"));
    }
    
    /**
     * test save method of CustomerController
     * @throws Exception
     */
    @Test
    public void testSaveCustomer() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/save")
                .param("id", "1")
                .param("first_name", "michael")
                .param("last_name", "yan");
        perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Override
    protected CustomerController getController() {
        return controller;
    }
    
}
