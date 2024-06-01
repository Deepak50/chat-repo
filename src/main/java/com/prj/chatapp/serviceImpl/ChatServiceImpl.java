package com.prj.chatapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.chatapp.dto.ChatDto;
import com.prj.chatapp.dto.ChatListDto;
import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.entity.Chat;
import com.prj.chatapp.repository.ChatRepository;
import com.prj.chatapp.service.ChatService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private ModelMapper m;
	
	@Override
	public ResponseDto saveChat(ChatDto chatDto) {
		Chat chat = chatRepository.save(m.map(chatDto, Chat.class));
		ResponseDto responseDto = new ResponseDto();
		
		responseDto.setStatusCode(200);
		responseDto.setStatus("Success");
		responseDto.setMessage("Successfully saved chat");
		responseDto.setData(chat);
		return responseDto;
	}
	
	public ResponseDto getChatList(String userId) {
		List<Object[]> chatList= chatRepository.getChatList(userId);
		
		List<ChatListDto> chatListDtos = new ArrayList<ChatListDto>();
		
		ResponseDto responseDto = new ResponseDto();
		for(Object[] chat: chatList) {
			String username = (String)chat[1];
			String profilePic = (String)chat[2];
			
			ChatListDto chatListDto = new ChatListDto();
			chatListDto.setName(username);;
			chatListDto.setProfilePic(profilePic);
			chatListDtos.add(chatListDto);
		}
		responseDto.setStatusCode(200);
		responseDto.setMessage("Successfully fetched");
		responseDto.setStatus("Success");
		responseDto.setData(chatListDtos);
		return responseDto;
	}
	
	public ResponseDto getChat(String fromUserId, String toUserId) {
	
		ResponseDto responseDto = new ResponseDto();
		List<Chat> chats = chatRepository.getChat(fromUserId, toUserId);
		responseDto.setStatusCode(200);
		responseDto.setData(chats);
		return responseDto;
	}
}
