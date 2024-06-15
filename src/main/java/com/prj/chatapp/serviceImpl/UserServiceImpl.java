package com.prj.chatapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.entity.Friends;
import com.prj.chatapp.entity.FriendsCk;
import com.prj.chatapp.entity.Userr;
import com.prj.chatapp.repository.FriendRepository;
import com.prj.chatapp.repository.UserRepository;
import com.prj.chatapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendRepository friendRepository;
	
	@Override
	public ResponseDto getLoggedInUserDetails(String user) {
		ResponseDto responseDto  = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setData(userRepository.findById(user));
		return responseDto;
	}
	
	@Override
	public ResponseDto addFriend(String userId ,String friendId) {
		ResponseDto responseDto  = new ResponseDto();
		responseDto.setStatusCode(200);
		
		Userr user = userRepository.findById(userId).get();
		Userr friend = userRepository.findById(friendId).get();
		
		Friends f = new Friends(new FriendsCk(user.getUserId(), friend.getUserId()));
		friendRepository.save(f);
		
		return responseDto;
	}
	
}
