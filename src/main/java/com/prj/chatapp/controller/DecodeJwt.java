package com.prj.chatapp.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/decodeJwt")
public class DecodeJwt {
	
	@PostMapping("/decode")
	public JSONObject decodeJwt(@RequestBody String token) throws UnsupportedEncodingException {
		String payload = token.split("\\.")[1];
		String jsonString = new String(Base64.decodeBase64(payload), "UTF-8");
		JSONObject json = new JSONObject(jsonString); 
		return json;
	}
}
