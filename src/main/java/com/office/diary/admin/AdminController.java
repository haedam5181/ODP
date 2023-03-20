package com.office.diary.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = {"", "/"}, method=RequestMethod.GET)
	public String home() {
		System.out.println("[AdminController] home() CALLED!!");
		
		String nextPage = "admin/home";
		
		return nextPage;
		
	}
	
}
