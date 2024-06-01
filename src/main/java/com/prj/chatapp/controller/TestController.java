package com.prj.chatapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController{

	@GetMapping("/test")
	public String test() {
		return "Testing";
	}
	
//	@GetMapping("/")
//	public String home(Principal usr) {
//		if (usr != null)
//			System.out.println("all: " + usr.toString());
//		
//		ModelAndView modelAndView = new ModelAndView();
//	    modelAndView.setViewName("index.html");
//	    return "Please Login";
		
//		return ("index");
//	}

	/*@GetMapping("/user")
	public ModelAndView user(Principal usr) {
		if (usr != null)
			System.out.println("all: " + usr.toString());
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index.html");
	    return modelAndView;
	}

	@GetMapping("/admin")
	public String admin(Principal usr) {
		if (usr != null)
			System.out.println("admin: " + usr.toString());
		return ("<h1> Welcome admin </h1>");
	}*/

}
