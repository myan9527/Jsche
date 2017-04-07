package org.jsche.web.controller;

import org.jsche.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SimpleController extends BasicController {

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

    @RequestMapping(value = "/test")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView("task/test");
        mav.addObject("data",new int[]{1,2,4,9});
        return mav;
    }
}
