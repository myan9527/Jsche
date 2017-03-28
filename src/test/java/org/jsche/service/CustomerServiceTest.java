package org.jsche.service;

import org.jsche.entity.Customer;
import org.jsche.repo.CustomerRepository;
import org.jsche.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {
    private CustomerService customerService;
    @Mock
    private CustomerRepository cp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl();
        Whitebox.setInternalState(customerService, "cp", cp);
    }

    @Test
    public void testFindByFirstName() {
        List<Customer> list = new ArrayList<>();
        Customer customer = mock(Customer.class);
        list.add(customer);
        when(cp.findByFirstName("michael")).thenReturn(list);
        Assert.assertTrue(customerService.findByFirstName("michael").size() == 1);
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = mock(Customer.class);
        customerService.save(customer);
        verify(cp).save(customer);
    }

}
