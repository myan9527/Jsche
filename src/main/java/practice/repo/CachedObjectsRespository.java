package practice.repo;

import java.util.List;
import org.springframework.stereotype.Component;
import practice.entity.City;

import practice.entity.Customer;

/**
 * add all objects cached here
 */
@Component
public interface CachedObjectsRespository {

    List<Customer> getAllCustomers();

    List<City> getAllCities();
}
