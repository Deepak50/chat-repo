package com.prj.chatapp.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/saveUser")
	public ResponseDto saveUser(@RequestHeader(name = "Authorization", required = false) String token, Principal usr) throws UnsupportedEncodingException {
		
		return loginService.saveUser(token,usr);
	}
}
