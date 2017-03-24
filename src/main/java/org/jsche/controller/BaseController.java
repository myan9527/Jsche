package org.jsche.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsche.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/**
 * All direct page forward goes here.
 */
public class BaseController {

    @RequestMapping("/")
    public String index(HttpSession session) {
        if(session.getAttribute(Constants.LOGIN_USER) != null){
            return "redirect:user/dashboard";
        }
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "user/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "user/register";
    }
}
