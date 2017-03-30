package org.jsche.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jsche.entity.Task;

/**
 * Created by myan on 2017/3/29.
 */
public interface TaskDao {
	
    List<Task> getTaskByUserId(@Param("userId") int userId);

    Task getTaskById(@Param("id") int id);

    void save(Task task);

    List<Task> getIncomingTasks(@Param("userId") int userId);
}
