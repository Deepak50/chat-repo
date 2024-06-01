package com.prj.chatapp.repository;

import com.prj.chatapp.dto.UserDto;

public interface RegistrationRepo {
	public void createUser(UserDto userDto);
}