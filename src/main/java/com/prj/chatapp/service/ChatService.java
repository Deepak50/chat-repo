package com.prj.chatapp.service;

import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;

import com.prj.chatapp.dto.ChatDto;
import com.prj.chatapp.dto.ResponseDto;


public interface ChatService {
	public ResponseDto saveChat(ChatDto chatDto);
	public ResponseDto getChatList(String userId);
	public ResponseDto getChat(String fromUserId, String toUserId);
}
