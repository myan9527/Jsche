package org.jsche.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.jsche.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {

    List<Task> getTaskByUserId(int userId, Pageable pageable);

    @Query(value = "SELECT * FROM tasks t where t.user_id = :userId and t.start_date <= now() + 3", nativeQuery = true)
    List<Task> getIncomingTasks(@Param("userId") int userId);
}
