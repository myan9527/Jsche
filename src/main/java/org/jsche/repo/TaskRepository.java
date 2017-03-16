package org.jsche.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.jsche.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> getTaskByUserId(int userId);
}
