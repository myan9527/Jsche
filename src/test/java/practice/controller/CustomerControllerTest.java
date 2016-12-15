/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import practice.entity.Customer;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import practice.repo.CustomerRepository;


/**
 * Sample code for rest controller test case.
 * @author myan
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class CustomerControllerTest extends WebAPIBaseTest<CustomerController>{
    @Mock
    private CustomerRepository cp;
    @InjectMocks
    private CustomerController controller;
    
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
     * Test of index method, of class BaseController.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetByFirstName() throws Exception {        
        RequestBuilder request = null;
        Customer customer= new Customer("michael", "yan");
        List<Customer> cuss = new ArrayList<>();
        cuss.add(customer);
        //mock first
        Mockito.when(cp.save(customer)).thenReturn(customer);
        Mockito.when(cp.findByFirstName("michael")).thenReturn(cuss);
        
        //save an customer first
        request = MockMvcRequestBuilders.post("/save")
                .param("id", "1")
                .param("first_name", "michael")
                .param("last_name", "yan");
        perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
        //then get it
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

    @Override
    protected CustomerController getController() {
        return controller;
    }
    
}
