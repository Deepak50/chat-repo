package com.prj.chatapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prj.chatapp.dto.UserDto;
import com.prj.chatapp.repository.RegistrationRepo;
import com.prj.chatapp.service.RegistrationService;


@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private RegistrationRepo registrationRepo;
	
	@Override
	public void createUser(UserDto userDto) {
		registrationRepo.createUser(userDto);
	}
}
