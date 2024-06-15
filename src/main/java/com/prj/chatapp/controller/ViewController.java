package com.prj.chatapp.controller;
import java.security.Principal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
public class ViewController {
	
	@GetMapping("/")
	public String home(Principal usr) {
		if (usr != null)
			System.out.println("all: " + usr.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index.html");
		return "Please Login";
	}
	
	
	@GetMapping("/user")
	public ModelAndView user(Principal usr) {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index.html");
	    return modelAndView;
	}
}
