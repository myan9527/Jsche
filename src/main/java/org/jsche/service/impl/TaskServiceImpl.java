package org.jsche.service.impl;

import org.jsche.common.ErrorMessage;
import org.jsche.common.exception.ServiceException;
import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;
import org.jsche.repo.TaskRepository;
import org.jsche.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository tp;

    @Override
    public TaskType[] buildTypeArray() {
        return TaskType.values();
    }

    @Override
    public List<Task> getUserTasks(int userId, Pageable pageable) {
        List<Task> tasks = tp.getTaskByUserId(userId, pageable);
        if (!tasks.isEmpty()) {
            Collections.sort(tasks);
        }
        return tasks;
    }

    @Override
    public void save(Task task) throws ServiceException {
        if (tp.findOne(task.getId()) != null) {
            throw new ServiceException(ErrorMessage.INVALID_OPERATION);
        }
        tp.save(task);
    }

    /**
     * Basic analysis here.
     */
    @Override
    public Map<String, Object> analysis(List<Task> tasks) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Integer> typesData = buildTypesData(tasks);
        result.put("type_data", typesData);
        return result;
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

    /**
     * Daily view for current user.
     */
    @Override
    public List<Task> getDailyTasks(Date data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Task> getIncomingTasks(int userId) {
        return tp.getIncomingTasks(userId);
    }

    @Override
    public Task getItem(int id) {
        return tp.findOne(id);
    }

}
