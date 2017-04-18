package org.jsche.web.service.impl;

import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.MethodLog;
import org.jsche.common.exception.ServiceException;
import org.jsche.entity.KeyValuePair;
import org.jsche.entity.Task;
import org.jsche.entity.TaskType;
import org.jsche.web.dao.TaskDao;
import org.jsche.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public TaskType[] buildTypeArray() {
        return TaskType.values();
    }

    @Override
    @Cacheable(value = "taskCache", key = "'user_'+#userId")
    @MethodLog
    public List<Task> getUserTasks(int userId) {
        return taskDao.getTaskByUserId(userId);
    }

    @Override
    @CacheEvict(value = {"taskCache", "extraDataCache", "incomingCache", "todayCountCache"},
            key = "'user_'+#task.getUserId()", allEntries = true)
    public void save(Task task) throws ServiceException {
        if (taskDao.getTaskById(task.getId()) != null) {
            throw new ServiceException(ErrorMessage.INVALID_OPERATION);
        }
        taskDao.save(task);
    }

    /**
     * Basic analysis here.
     */
    @Override
    public Map<String, Object> analysis(List<Task> tasks) {
        Map<String, Object> result = new HashMap<>();
        Map<TaskType, Long> typesData = buildTypesData(tasks);
        result.put("type_data", typesData);
        result.put("priority_data", buildPriotyData(tasks));
        return result;
    }

    @Override
    public int[] buildPriotyData(List<Task> tasks) {
        int[] result = new int[4];
        Map<Integer, Long> res = tasks.stream().
                collect(Collectors.groupingBy(Task::getPriority, Collectors.counting()));
        for (int i = 0; i < res.size(); i++) {
            result[i] = Math.toIntExact(res.get(i));
        }

        return result;
    }

    @Override
    public List<KeyValuePair> getWeeklyTrendData(int userId) {
        List<KeyValuePair> data = taskDao.getWeeklyTrendData(userId);
        List<KeyValuePair> result = this.getWeekSerial();
        for (int i = 0; i < result.size(); i++) {
            KeyValuePair kv = result.get(i);
            for (KeyValuePair datum : data) {
                if (datum.getKey().equalsIgnoreCase(kv.getKey())) {
                    result.set(i, datum);
                }
            }
        }

        return result;
    }

    @Cacheable("weekSerialCache")
    private List<KeyValuePair> getWeekSerial() {
        return taskDao.getWeekSerial();
    }

    @Override
    @Cacheable(value = "todayCountCache", key = "'user_'+#userId")
    public int getTodayTaskCount(int userId) {
        return userId > 0 ? taskDao.getTodayTaskCount(userId) : 0;
    }

    @Override
    @Cacheable(value = "extraDataCache", key = "'user_'+#userId")
    public Map<String, Integer> getExtraData(int userId) {
        return userId > 0 ? taskDao.getExtraData(userId) : null;
    }

    @Override
    public List<Task> getUserTasksPages(Map<String, Object> params) {
        return taskDao.getUserTaskPages(params);
    }

    @Override
    @Cacheable("taskCount")
    public int getUserTaskCount(int userId) {
        return this.getUserTasks(userId).size();
    }

    private Map<TaskType, Long> buildTypesData(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getTaskType, Collectors.counting()));
    }

    @Override
    @Cacheable(value = "incomingCache", key = "'user_'+#userId")
    public List<Task> getIncomingTasks(int userId) {
        return taskDao.getIncomingTasks(userId);
    }

    @Override
    public Task getItem(int id) {
        return taskDao.getTaskById(id);
    }

}
