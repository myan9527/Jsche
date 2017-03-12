package org.jsche.repo;

import org.jsche.entity.SystemUsage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUsageRepository extends CrudRepository<SystemUsage, Integer> {

}
