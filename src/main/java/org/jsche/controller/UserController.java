/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.RequiredLogin;
import org.jsche.common.util.AppUtil;
import org.jsche.entity.User;
import org.jsche.service.TaskService;
import org.jsche.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author myan
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(HttpServletRequest request, String email, String password) {
        ModelAndView mav = new ModelAndView("user/login");
        User user = userService.getUserByEmail(email);
        if(user == null){
            mav.addObject(Constants.ERROR_ATTR_NAME,ErrorMessage.NO_SUCH_USER);
        }else{
            if(user.getPassword().equals(AppUtil.getHexPassword(password))){
                //load basic data here.
                userService.updateLastLogin(user);
                mav.setViewName("redirect:/user/dashboard");
                request.getSession().setAttribute(Constants.LOGIN_USER, user);
            }else{
                mav.addObject(Constants.ERROR_ATTR_NAME,ErrorMessage.INVALID_PASSWORD);
            }
        }
        return mav;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView processRegister(HttpServletRequest request, User user, String repassword) {
        ModelAndView mav = new ModelAndView("user/register");
        if (user.getPassword().length() == 0) {
            mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.PASSWORD_REQUIRED);
            return mav;
        } else if (!user.getPassword().equals(repassword)) {
            mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.UNMATCHED_PASSWORD);
            return mav;
        }
        if (userService.getUserByEmail(user.getEmail()) != null) {
            mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.EMAIL_REGISTERED);
            return mav;
        }
        mav.setViewName("redirect:/login");
        user.setPassword(AppUtil.getHexPassword(user.getPassword()));
        userService.save(user);

        return mav;
    }
    
    @RequestMapping(value = "/dashboard")
    @RequiredLogin
    public ModelAndView dashboard(HttpSession session){
        ModelAndView mav = new ModelAndView("user/dashboard");
        User loginUser = (User) session.getAttribute(Constants.LOGIN_USER);
        mav.addObject("tasks", taskService.getUserTasks(loginUser.getId()));
        /*
        if(loginUser != null){
        }else{
            mav.setViewName("redirect:/login");
            mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.LOGIN_REQUIRED);
        }
        */
        return mav;
    }
    
    @RequestMapping(value = "/profile")
    @RequiredLogin
    public ModelAndView profile(HttpSession session){
    	ModelAndView mav = new ModelAndView("user/profile");
    	return mav;
    }
    
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute(Constants.LOGIN_USER);
        return "index";
    }
}
