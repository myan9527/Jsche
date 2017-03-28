package org.jsche.controller;

import org.jsche.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    @RequestMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute(Constants.LOGIN_USER) != null) {
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
