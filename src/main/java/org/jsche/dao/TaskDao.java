package org.jsche.dao;

import org.apache.ibatis.annotations.Param;
import org.jsche.entity.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/3/29.
 */
public interface TaskDao {
	
    List<Task> getTaskByUserId(@Param("userId") int userId);

    Task getTaskById(@Param("id") int id);

    void save(Task task);

    List<Task> getIncomingTasks(@Param("userId") int userId);

    List<Map<String,Integer>> getWeeklyTrendData(@Param("userId") int userId);
}
