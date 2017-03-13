package org.jsche.repo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.jsche.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {
    @Mock
    private CustomerRepository cp;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFindByFirstName(){
        Customer c = mock(Customer.class);
        List<Customer> list = new ArrayList<>();
        list.add(c);
                
        when(cp.findByFirstName("Michael")).thenReturn(list);
        List<Customer> result = cp.findByFirstName("Michael");
        assertNotNull(result);
        assertTrue(result.contains(c));
    }
    
}
