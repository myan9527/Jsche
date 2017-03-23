package org.jsche.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsche.common.ErrorMessage;
import org.jsche.common.exception.ServiceException;
import org.jsche.entity.Task;
import org.jsche.entity.Task.TaskType;
import org.jsche.repo.TaskRepository;
import org.jsche.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository tp;

    @Override
    public TaskType[] buildTypeArray() {
        return TaskType.values();
    }

    @Override
    public List<Task> getUserTasks(int userId) {
        List<Task> tasks = tp.getTaskByUserId(userId);
        if(!tasks.isEmpty()){
            Collections.sort(tasks);
        }
        return tasks;
    }
    
    @Override
    public void save(Task task) throws ServiceException{
        if(tp.findOne(task.getId()) != null){
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
        Map<String, Integer> typesData = new HashMap<>();
        int f =0;
        int w = 0;
        int sa = 0;
        int si = 0;
        int o = 0;
        for (Task task : tasks) {
            //basic type
            switch (task.getTaskType()) {
            case FAMILY_ISSUE:
                typesData.put(TaskType.FAMILY_ISSUE.getTypeName(), ++f);
                break;
            case WORK_TASK:
                typesData.put(TaskType.WORK_TASK.getTypeName(), ++w);
                break;
            case SOCIAL_ACTIVITY:
                typesData.put(TaskType.SOCIAL_ACTIVITY.getTypeName(), ++sa);
                break;
            case SELF_IMPROVEMENT:
                typesData.put(TaskType.SELF_IMPROVEMENT.getTypeName(), ++si);
                break;
            default:
                typesData.put(TaskType.OTHER_ISSUE.getTypeName(), ++o);
                break;
            }
            //status
            switch (task.getStatus()) {
            case 0:
                
                break;
            case 1:
                
                break;

            default:
                break;
            }
        }
        result.put("type_data", typesData);
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

}
