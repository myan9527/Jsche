package org.jsche.repo;

import java.util.List;
import org.jsche.entity.City;
import org.jsche.entity.Customer;
import org.springframework.stereotype.Component;

/**
 * add all objects cached here
 */
@Component
public interface CachedObjectsRespository {

    List<Customer> getAllCustomers();

    List<City> getAllCities();
}
