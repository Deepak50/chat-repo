package com.prj.chatapp.controller;

import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.util.buf.StringUtils;
//import org.springframework.security.core.AuthenticatedPrincipal;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.chatapp.dto.ResponseDto;


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
