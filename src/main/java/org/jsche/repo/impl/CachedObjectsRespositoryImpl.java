package org.jsche.repo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.jsche.entity.City;
import org.jsche.entity.Customer;
import org.jsche.repo.CachedObjectsRespository;
import org.jsche.repo.CityRepository;
import org.jsche.repo.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

public class CachedObjectsRespositoryImpl implements CachedObjectsRespository {

    private final Object lock = new Object();

    @Autowired
    private CustomerRepository cp;
    @Resource(name = "cityRepo")
    private CityRepository cityRepo;

    @Override
    @Cacheable("customers")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        synchronized (lock) {
            Iterator<Customer> it = cp.findAll().iterator();
            while (it.hasNext()) {
                customers.add(it.next());
            }
        }
        return customers;
    }

    @Override
    @Cacheable("cities")
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        synchronized(lock) {
            Iterator<City> it = cityRepo.findAll().iterator();
            while(it.hasNext()){
                cities.add(it.next());
            }
        }
        return cities;
    }

}