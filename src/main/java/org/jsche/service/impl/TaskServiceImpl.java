package org.jsche.service.impl;

import java.util.Collections;
import java.util.List;

import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;
import org.jsche.repo.TaskRepository;
import org.jsche.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taskService")
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskRepository tp;
	
	@Override
	public TaskType[] buildTypeArray() {
		return TaskType.values();
	}

	@Override
	public List<Task> getUserTasks(int userId) {
		List<Task> tasks = tp.getUserTasks(userId);
		Collections.sort(tasks);
		return tasks;
	}

}
