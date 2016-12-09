package practice.repo;

import java.util.List;

import practice.entity.Customer;

/**
 * add all objects cached here
 */
public interface CachedObjectsRespository {
	List<Customer> getAllCustomers();
	
}
