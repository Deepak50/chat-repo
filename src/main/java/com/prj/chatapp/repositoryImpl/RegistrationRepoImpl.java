package com.prj.chatapp.repositoryImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.chatapp.dto.UserDto;
import com.prj.chatapp.entity.Userr;
import com.prj.chatapp.repository.RegistrationRepo;
import com.prj.chatapp.repository.UserRepository;


@Repository
public class RegistrationRepoImpl implements RegistrationRepo {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void createUser(UserDto userDto) {
		userRepository.save(modelMapper.map(userDto, Userr.class));
	}

}
