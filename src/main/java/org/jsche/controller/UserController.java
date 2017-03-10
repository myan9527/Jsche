/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.common.util.AppUtil;
import org.jsche.entity.User;
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
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView processRegister(HttpServletRequest request, User user){
    	ModelAndView mav = new ModelAndView();
    	if(user.getPassword().trim().length() == 0){
    	    mav.setViewName("user/register");
    	    return mav;
    	}
    	if(userService.getUserByEmail(user.getEmail()) != null){
    	    mav.setViewName("user/register");
    	    mav.addObject(Constants.ERROR_ATTR_NAME, ErrorMessage.EMAIL_REGISTERED);
    	    return mav;
    	}
    	mav.setViewName("user/dashboard");
    	user.setPassword(AppUtil.getHexPassword(user.getPassword()));
    	userService.save(user);
    	return mav;
    }
}
