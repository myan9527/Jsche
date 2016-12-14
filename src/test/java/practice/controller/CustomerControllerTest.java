/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import practice.Application;
import practice.entity.Customer;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Sample code for rest controller test case.
 * @author myan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CustomerControllerTest {
    private MockMvc mvc;
    Customer c = null;
    
    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(new CustomerController()).build();
    }
    
    @After
    public void tearDown() {
        //cp.delete(c);
    }

    /**
     * Test of index method, of class BaseController.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetByFirstName() throws Exception {        
        RequestBuilder request = null;
        
        //save an customer first
        request = MockMvcRequestBuilders.post("/save")
                .param("id", "1")
                .param("first_name", "michael")
                .param("last_name", "yan");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
        //then get it
        request = MockMvcRequestBuilders.get("/get/test");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("test"));
    }
    
}
