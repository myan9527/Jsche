package practice.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import practice.entity.Customer;

@Transactional
@Component
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByFirstName(String firstName);
}
