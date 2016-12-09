package practice.repo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import practice.entity.Customer;
import practice.repo.CachedObjectsRespository;
import practice.repo.CustomerRepository;

public class CachedObjectsRespositoryImpl implements CachedObjectsRespository{
	private Object lock;
	
	@Autowired
	private CustomerRepository cp;
	
	@Override
	@Cacheable("customers")
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		synchronized (lock) {
			Iterator<Customer> it = cp.findAll().iterator();
			while(it.hasNext()){
				customers.add(it.next());
			}
		}
		return customers;
	}

}
