package com.prj.chatapp.service;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import com.prj.chatapp.dto.ResponseDto;

public interface LoginService {
	public ResponseDto saveUser(String token, Principal user) throws UnsupportedEncodingException;
}
