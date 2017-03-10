package org.jsche.controller;

import javax.servlet.http.HttpServletRequest;

import org.jsche.common.Constants;
import org.jsche.common.token.TokenHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
/**
 * All direct page forward goes here.
 */
public class BaseController {
	
    @RequestMapping("/")
    public String index(ModelMap map){
        return "index";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("user/login");
        mav.addObject(Constants.TOKEN_ATTR_NAME, TokenHandler.generateToken(request.getSession()));
        return mav;
    } 
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("user/register");
        mav.addObject(Constants.TOKEN_ATTR_NAME, TokenHandler.generateToken(request.getSession()));
        return mav;
    } 
}
