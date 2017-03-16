package org.jsche.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;

public interface TaskService {
	TaskType[] buildTypeArray();
	
	List<Task> getUserTasks(int userId);
	
	List<Task> getDailyTasks(Date data);
	
	void save(Task task);
	
	Map<String, Object> analysis(List<Task> tasks);
}
