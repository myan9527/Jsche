package org.jsche.web.controller;

import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.RequiredLogin;
import org.jsche.common.exception.ServiceException;
import org.jsche.common.util.AppUtil;
import org.jsche.common.validation.constraints.CheckValid;
import org.jsche.entity.Task;
import org.jsche.entity.TaskType;
import org.jsche.entity.User;
import org.jsche.web.dao.Pager;
import org.jsche.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/task")
public class TaskController extends BasicController {
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
    public ModelAndView createTask(@CheckValid Task task,
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

    @RequestMapping(value = "/tasks/{page}", method = RequestMethod.GET)
    @RequiredLogin
    @ResponseBody
    public ModelMap getUserTaskPageData(HttpSession session, @PathVariable("page") int pageNumber){
        ModelMap result = new ModelMap();
        Optional<User> userOptional = Optional.ofNullable((User)session.getAttribute(Constants.LOGIN_USER));
        if(userOptional.isPresent()){
            User loginUser = userOptional.get();
            Map<String, Object> params = new HashMap<>();
            params.put("userId", loginUser.getId());
            Pager pager = new Pager(pageNumber, taskService.getUserTaskCount(loginUser.getId()), Constants.PAGE_SIZE);
            List<Task> tasks = taskService.getUserTasksPages(params);
            result.put("pager", pager);
            result.put("tasks", tasks);
        }
        return result;
    }


    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @RequiredLogin
    public Task getItem(@PathVariable(name = "id") int taskId) {
        return taskService.getItem(taskId);
    }

}
