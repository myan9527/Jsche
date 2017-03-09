/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author myan
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("callback")String callback){
        ModelAndView mav = new ModelAndView("login");
        if(StringUtils.isEmpty(callback) || "null".equalsIgnoreCase(callback)){
            callback = "";
        }
        mav.addObject("callback", callback);
        return mav;
    } 
}
