/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.web.controller;

import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.RequiredLogin;
import org.jsche.common.util.AppUtil;
import org.jsche.entity.User;
import org.jsche.web.service.TaskService;
import org.jsche.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author myan
 */
@Controller
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(HttpServletRequest request, String email, String password) {
        ModelAndView mav = new ModelAndView("user/login");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.NO_SUCH_USER);
        } else {
            if (user.getPassword().equals(AppUtil.getHexPassword(password))) {
                // load basic data here.
                userService.updateLastLogin(user);
                mav.setViewName("redirect:/user/dashboard");
                request.getSession().setAttribute(Constants.LOGIN_USER, user);
            } else {
                mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.INVALID_PASSWORD);
            }
        }
        return mav;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView processRegister(HttpServletRequest request, User user, String repassword) {
        ModelAndView mav = new ModelAndView("user/register");
        mav.addObject("user", user);
        if (!user.getPassword().equals(repassword)) {
            mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.UNMATCHED_PASSWORD);
        } else if (userService.getUserByEmail(user.getEmail()) != null) {
            mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.EMAIL_REGISTERED);
        }
        if (mav.getModel().get(Constants.ERROR_ATTR_NAME) != null) {
            return mav;
        }
        mav.setViewName("redirect:/login");
        user.setPassword(AppUtil.getHexPassword(user.getPassword()));
        userService.save(user);

        return mav;
    }

    @RequestMapping(value = "/dashboard")
    @RequiredLogin
    public ModelAndView dashboard(HttpSession session) {
        ModelAndView mav = new ModelAndView("user/dashboard");
        User loginUser = (User) session.getAttribute(Constants.LOGIN_USER);
        if (loginUser != null) {
            mav.addObject("tasks", taskService.getUserTasks(loginUser.getId()));
            //Fixed by ehcache
            mav.addObject("incomings", taskService.getIncomingTasks(loginUser.getId()).size());
            mav.addObject("todayCounts",taskService.getTodayTaskCount(loginUser.getId()));
            mav.addObject("extraData",taskService.getExtraData(loginUser.getId()));
            //always return 7 elements {Apr 5:1,Apr 6:2 ...}
            mav.addObject("trends",taskService.getWeeklyTrendData(loginUser.getId()));
        }
        return mav;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @RequiredLogin
    public ModelAndView profile(HttpSession session) {
        ModelAndView mav = new ModelAndView("user/profile");
        User loginUser = (User) session.getAttribute(Constants.LOGIN_USER);
        if(loginUser != null){
            mav.addObject("user", userService.getUserById(loginUser.getId()));
        }
        return mav;
    }

    @RequiredLogin
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView updateProfile(User user) {
        userService.save(user);
        return null;
    }

    @RequiredLogin
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public ModelAndView updateAvatar(HttpServletRequest request, @RequestParam(value = "avatar") MultipartFile file) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
        if (user != null) {
            user.setCustomizedAvatar(true);
            user.setAvatar(path + File.pathSeparator + fileName);
            userService.updateUserAvatar(user);
        }
        return null;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.LOGIN_USER);
        return "index";
    }
}
