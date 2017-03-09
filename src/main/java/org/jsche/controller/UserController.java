/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String register(){
        return "user/register";
    } 
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
}
