/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.entity.City;

/**
 *
 * @author myan
 */
@Transactional
@Component
public interface CityRepository extends CrudRepository<City, Integer>{
    List<City> findByName(String name);
}
