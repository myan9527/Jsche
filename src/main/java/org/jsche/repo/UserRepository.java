package org.jsche.repo;

import javax.transaction.Transactional;

import org.jsche.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User getUserByEmail(String email);
}
