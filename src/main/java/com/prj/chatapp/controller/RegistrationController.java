package com.prj.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prj.chatapp.dto.UserDto;
import com.prj.chatapp.service.RegistrationService;

@RestController
@RequestMapping("/")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/createUser")
	public void createUser(@RequestBody UserDto userDto) {
		registrationService.createUser(userDto);
	}
}
