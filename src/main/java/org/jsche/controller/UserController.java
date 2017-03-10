/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView processRegister(HttpServletRequest request, HttpServletResponse response){
    	ModelAndView mav = new ModelAndView();
    	try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	mav.setViewName("index");
    	return mav;
    }
}
