package com.prj.chatapp.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.entity.Userr;
import com.prj.chatapp.repository.UserRepository;
import com.prj.chatapp.service.LoginService;
import com.prj.chatapp.serviceUtil.ExtractToken;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseDto saveUser(@RequestHeader(name = "Authorization", required = false) String token, Principal usr) throws UnsupportedEncodingException {
ResponseDto responseDto = new ResponseDto();
		
		ExtractToken e = new ExtractToken();
		JSONObject attributes =  e.decodeJwt(token);

		
		Userr user = new Userr();
		user.setUserId((String) attributes.get("email"));
		user.setActive(true);
		user.setEmail((String) attributes.get("email"));
		user.setJoinedDate(null);
		user.setPassword(null);
		user.setProfilePic((String) attributes.get("picture"));
		user.setRoles(null);
		user.setUserName((String) attributes.get("name"));
		
		userRepository.save(user);
		
		return null;
		
	}

}
