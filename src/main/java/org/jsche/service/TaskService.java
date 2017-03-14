package org.jsche.service;

import java.util.List;

import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;

public interface TaskService {
	TaskType[] buildTypeArray();
	
	List<Task> getUserTasks(int userId);
}
