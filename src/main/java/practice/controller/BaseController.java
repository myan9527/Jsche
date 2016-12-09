package practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	@RequestMapping("/index")
	public String index(ModelMap map){
		map.addAttribute("result", "Some test content");
		return "index";
		
	}
}
