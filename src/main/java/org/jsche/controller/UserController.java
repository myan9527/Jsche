/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.controller;

import org.jsche.common.token.TokenManager;
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
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    } 
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView("user/register");
        mav.addObject(TokenManager.TOKEN_ATTR_NAME, TokenManager.getCsrfToken().getTokenizer());
        return mav;
    } 
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView processRegister(){
    	TokenManager.getCsrfToken().expires();
    	ModelAndView mav = new ModelAndView();
    	if(!TokenManager.getCsrfToken().isValid()){
    		mav.setViewName("error/form");
    	}else{
    		//nomal process
    		mav.setViewName("user/login");
    	}
    	return mav;
    }
}
