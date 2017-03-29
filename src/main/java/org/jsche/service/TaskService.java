package org.jsche.service;

import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;

import java.util.List;
import java.util.Map;

public interface TaskService {
    TaskType[] buildTypeArray();

    List<Task> getIncomingTasks(int userId);

    List<Task> getUserTasks(int userId);

    void save(Task task);

    Map<String, Object> analysis(List<Task> tasks);

    Task getItem(int id);

    int[] buildPriotyData(List<Task> tasks);

}
