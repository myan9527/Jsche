/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.repo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author myan
 */
public class CacheRepoTestCase extends RepoBaseTest{
    @Autowired
    private CachedObjectsRespository cachedRepo;
    @Autowired
    private CustomerRepository cp;
    @Autowired
    private CityRepository cityRepo;
    
    @Test
    public void testGetAllCustomers(){
        Customer c1 = new Customer("michael", "yan");
        cp.save(c1);
        Assert.assertEquals(1, cachedRepo.getAllCustomers().size());
    }
}
