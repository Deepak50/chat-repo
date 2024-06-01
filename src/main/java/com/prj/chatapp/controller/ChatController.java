package com.prj.chatapp.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.prj.chatapp.dto.ChatDto;
import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.model.MsgData;
import com.prj.chatapp.service.ChatService;
import com.prj.chatapp.serviceUtil.ExtractToken;

@RestController
public class ChatController {

	public static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

	private final SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private ChatService chatService;
	
	public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@MessageMapping("/sendToAll")
	@SendTo("/topic/sendToAll")
	public String sendToAll(@Payload MsgData msgData, Principal principal) {
		LOGGER.error(principal.getName());
		return msgData.getMessage();
	}

	@MessageMapping("/message")
	@SendTo("/")
	public void greeting(MsgData msgData) {
		LOGGER.error("To user : " + msgData.getTo());
		System.out.println("Testing sysout.......");
		simpMessagingTemplate.convertAndSendToUser(msgData.getTo(), "/msg", msgData.getMessage());
		// simpMessagingTemplate.convertAndSend("/user/ramesh/msg",
		// msgData.getMessage());
	}
	
	
	@GetMapping("/getUsername")
	public String getUsername(Principal principal){
		return principal.getName();
	}
	
	@PostMapping("/saveChat")
	public ResponseDto saveChat(@RequestBody ChatDto chatDto) {
		return chatService.saveChat(chatDto);
	}
	
	@GetMapping("/getChatList/{userId}")
	public ResponseDto getChatList(@RequestHeader(name = "Authorization", required = false) String token, Principal usr, @PathVariable("userId") String userId) throws UnsupportedEncodingException {
		//comment the next 3 lines when calling thro postman
		ExtractToken e = new ExtractToken();
		JSONObject attributes =  e.decodeJwt(token);

		userId = (String) attributes.get("email");
		
		
		System.out.println("acc token : "+token);
		return chatService.getChatList(userId);
	}
	
	@GetMapping("/getChat/{toUserId}")
	public ResponseDto getChat(@RequestHeader(name = "Authorization", required = false) String token, Principal usr, @PathVariable("toUserId") String toUserId) throws UnsupportedEncodingException {
		
		ExtractToken e = new ExtractToken();
		JSONObject attributes =  e.decodeJwt(token);
		String fromUserId = (String) attributes.get("email");
		
		
		return chatService.getChat(fromUserId, toUserId);
	}
	
	
}