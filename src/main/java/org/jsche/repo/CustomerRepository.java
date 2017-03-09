package org.jsche.repo;

import java.util.List;
import org.jsche.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service(value = "cityDao")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByFirstName(String firstName);
}
