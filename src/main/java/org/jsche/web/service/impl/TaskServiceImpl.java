package org.jsche.web.service.impl;

import org.jsche.common.ErrorMessage;
import org.jsche.common.exception.ServiceException;
import org.jsche.entity.KeyValuePair;
import org.jsche.web.dao.TaskDao;
import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;
import org.jsche.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public TaskType[] buildTypeArray() {
        return TaskType.values();
    }

    @Override
    @Cacheable(value = "taskCache", key = "'task_'+#userId")
    public List<Task> getUserTasks(int userId) {
        List<Task> tasks = taskDao.getTaskByUserId(userId);
//        if (!tasks.isEmpty()) {
//            Collections.sort(tasks);
//        }
        return tasks;
    }

    @Override
    @CachePut(value = "taskCache", key = "'task_'+#userId")
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
        Map<String, Integer> typesData = buildTypesData(tasks);
        result.put("type_data", typesData);
        result.put("priority_data", buildPriotyData(tasks));
        return result;
    }

    @Override
    public int[] buildPriotyData(List<Task> tasks) {
        int[] result = new int[4];
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        for (Task task : tasks) {
            switch (task.getPriority()) {
                case 0:
                    p1++;
                    break;
                case 1:
                    p2++;
                    break;
                case 2:
                    p3++;
                    break;
                default:
                    p4++;
                    break;
            }
        }
        result[0] = p1;
        result[1] = p2;
        result[2] = p3;
        result[3] = p4;
        return result;
    }

    @Override
    public List<KeyValuePair> getWeeklyTrendData(int userId) {
        List<KeyValuePair> data = taskDao.getWeeklyTrendData(userId);
        List<KeyValuePair> result = this.getWeekSerial();
        for (int i = 0; i < result.size(); i++) {
            KeyValuePair kv = result.get(i);
            for (KeyValuePair datum : data) {
                if(datum.getKey().equalsIgnoreCase(kv.getKey())){
                    result.set(i,datum);
                }
            }
        }

        return result;
    }

    @Cacheable("weekSerial")
    private List<KeyValuePair> getWeekSerial() {
        return taskDao.getWeekSerial();
    }

    @Override
    @Cacheable(value = "todayCountCache",key = "'today_'+#userId")
    public int getTodayTaskCount(int userId) {
        return userId > 0 ? taskDao.getTodayTaskCount(userId) : 0;
    }

    @Override
    @Cacheable(value = "extraDataCache", key = "'extra_'+#userId")
    public Map<String, Integer> getExtraData(int userId) {
        return userId > 0 ? taskDao.getExtraData(userId): null;
    }

    public Map<String, Integer> buildTypesData(List<Task> tasks) {
        Map<String, Integer> result = new HashMap<>();
        int f = 0;
        int w = 0;
        int sa = 0;
        int si = 0;
        int o = 0;
        for (Task task : tasks) {
            //basic type
            switch (task.getTaskType()) {
                case FAMILY_ISSUE:
                    result.put(TaskType.FAMILY_ISSUE.getTypeName(), ++f);
                    break;
                case WORK_TASK:
                    result.put(TaskType.WORK_TASK.getTypeName(), ++w);
                    break;
                case SOCIAL_ACTIVITY:
                    result.put(TaskType.SOCIAL_ACTIVITY.getTypeName(), ++sa);
                    break;
                case SELF_IMPROVEMENT:
                    result.put(TaskType.SELF_IMPROVEMENT.getTypeName(), ++si);
                    break;
                default:
                    result.put(TaskType.OTHER_ISSUE.getTypeName(), ++o);
                    break;
            }

        }

        return result;
    }

    @Override
    @Cacheable(value = "incomingCache", key = "'task_'+#userId")
    public List<Task> getIncomingTasks(int userId) {
        return taskDao.getIncomingTasks(userId);
    }

    @Override
    public Task getItem(int id) {
        return taskDao.getTaskById(id);
    }

}
