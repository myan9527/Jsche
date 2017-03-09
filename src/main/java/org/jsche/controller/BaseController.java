package org.jsche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/**
 * All direct page forward goes here.
 */
public class BaseController {
	
    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("result", "Some test content");
        return "index";
    }
}
