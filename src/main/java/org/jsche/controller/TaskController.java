package org.jsche.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsche.common.Constants;
import org.jsche.common.annotation.RequiredLogin;
import org.jsche.entity.Task;
import org.jsche.entity.User;
import org.jsche.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequiredLogin
    public String newTask() {
        return "task/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequiredLogin
    public ModelAndView createTask(Task task) {
        ModelAndView mav = new ModelAndView("user/dashboard");
        taskService.save(task);

        return mav;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    @RequiredLogin
    @ResponseBody
    public String getTaskStatistics(HttpServletRequest request){
        Gson gson = new Gson();
        User loginUser = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
        List<Task> tasks = null;
        Map<String, Object> result = null;
        if(loginUser!=null){
            tasks = taskService.getUserTasks(loginUser.getId());
            if(!tasks.isEmpty()){
                result = taskService.analysis(tasks);
            }
        }
        return gson.toJson(result);
    }
}
