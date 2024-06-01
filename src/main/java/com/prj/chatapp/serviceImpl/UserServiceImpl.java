package com.prj.chatapp.serviceImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.repository.UserRepository;
import com.prj.chatapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseDto getLoggedInUserDetails(String user) {
		ResponseDto responseDto  = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setData(userRepository.findById(user));
		return responseDto;
	}
}
