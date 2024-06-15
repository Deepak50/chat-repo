package com.prj.chatapp.service;

import com.prj.chatapp.dto.ResponseDto;

public interface UserService {
	public ResponseDto getLoggedInUserDetails(String user);
	public ResponseDto addFriend(String userId, String friendId);
}
