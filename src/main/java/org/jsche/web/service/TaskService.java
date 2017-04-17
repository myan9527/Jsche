package org.jsche.web.service;

import org.jsche.entity.KeyValuePair;
import org.jsche.entity.Task;
import org.jsche.entity.TaskType;

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

    List<KeyValuePair> getWeeklyTrendData(int userId);

    int getTodayTaskCount(int userId);

    Map<String, Integer> getExtraData(int userId);

    int getUserTaskCount(int userId);

    List<Task> getUserTasksPages(Map<String, Object> params);
}
