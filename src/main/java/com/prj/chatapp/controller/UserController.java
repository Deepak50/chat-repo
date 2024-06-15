package com.prj.chatapp.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.service.UserService;
import com.prj.chatapp.serviceUtil.ExtractToken;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getLoggedInUserDetails")
	public ResponseDto getLoggedInUserDetails(@RequestHeader(name = "Authorization", required = false) String token, Principal user) throws UnsupportedEncodingException {
		ExtractToken e = new ExtractToken();
		JSONObject attributes =  e.decodeJwt(token);
		
		String userId = (String) attributes.get("email");
		return userService.getLoggedInUserDetails(userId);
	}
	
	@GetMapping("/addFriend")
	public ResponseDto getLoggedInUserDetails(@RequestHeader(name = "Authorization", required = false) String token, String friendId) throws UnsupportedEncodingException{
		ExtractToken e = new ExtractToken();
		JSONObject attributes =  e.decodeJwt(token);
		
		String userId = (String) attributes.get("email");
		return userService.addFriend(userId, friendId);
	}
	
}
