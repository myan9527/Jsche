package org.jsche.web.controller;

import com.google.gson.Gson;
import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.RequiredLogin;
import org.jsche.common.exception.ServiceException;
import org.jsche.common.util.AppUtil;
import org.jsche.entity.Task;
import org.jsche.entity.TaskType;
import org.jsche.entity.User;
import org.jsche.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController extends BasicController {
    private final Gson gson = new Gson();

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequiredLogin
    public ModelAndView newTask(HttpSession session) {
        ModelAndView mav = new ModelAndView("task/create");
        mav.addObject("types", taskService.buildTypeArray());
        User loginUser = (User) session.getAttribute(Constants.LOGIN_USER);
        if (loginUser != null) {
            List<Task> tasks = taskService.getIncomingTasks(loginUser.getId());
            mav.addObject("incomings", tasks);
            mav.addObject("countTip", tasks.size());
            mav.addObject("parray",taskService.buildPriotyData(tasks));
        }
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequiredLogin
    public ModelAndView createTask(Task task,
                                   @RequestParam(name = "start_date") String startDate,
                                   @RequestParam(name = "task_type") String type, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/user/dashboard");
        User loginUser = (User) session.getAttribute(Constants.LOGIN_USER);
        if (loginUser != null) {
            task.setUserId(loginUser.getId());
            if (startDate.trim().length() > 0) {
                task.setStartDate(AppUtil.parseDate(startDate));
            }
            if (Integer.valueOf(type) != -1) {
                task.setTaskType(TaskType.values()[Integer.valueOf(type)]);
            }
            taskService.save(task);
            return mav;
        }
        throw new ServiceException(ErrorMessage.INVALID_OPERATION);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    @RequiredLogin
    @ResponseBody
    public String getTaskStatistics(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
        List<Task> tasks;
        Map<String, Object> result = null;
        if (loginUser != null) {
            tasks = taskService.getUserTasks(loginUser.getId());
            if (!tasks.isEmpty()) {
                result = taskService.analysis(tasks);
            }
        }
        return gson.toJson(result);
    }

    @RequestMapping(value = "/incoming", method = RequestMethod.GET)
    @RequiredLogin
    @ResponseBody
    public String getIncomingTasks(@RequestParam(name = "user_id") int userId) {
        List<Task> tasks = taskService.getIncomingTasks(userId);
        if (tasks != null) {
            return gson.toJson(tasks);
        }
        return null;
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @RequiredLogin
    public Task getItem(@PathVariable(name = "id") int taskId) {
        return taskService.getItem(taskId);
    }

}
