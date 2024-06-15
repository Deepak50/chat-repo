package com.prj.chatapp.service;

import com.prj.chatapp.dto.ChatDto;
import com.prj.chatapp.dto.ResponseDto;


public interface ChatService {
	public ResponseDto saveChat(ChatDto chatDto);
	public ResponseDto getChatList(String userId);
	public ResponseDto getChat(String fromUserId, String toUserId);
	public ResponseDto getFriends(String userId);
	public ResponseDto getEverything(String userId);
}
