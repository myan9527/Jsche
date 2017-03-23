package org.jsche.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;
import org.springframework.data.domain.Pageable;

public interface TaskService {
	TaskType[] buildTypeArray();
	
	List<Task> getIncomingTasks(int userId);
	
	List<Task> getUserTasks(int userId, Pageable pageable);
	
	List<Task> getDailyTasks(Date data);
	
	void save(Task task);
	
	Map<String, Object> analysis(List<Task> tasks);
}
