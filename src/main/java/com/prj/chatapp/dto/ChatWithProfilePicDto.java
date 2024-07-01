package com.prj.chatapp.dto;

import java.util.List;

import com.prj.chatapp.entity.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatWithProfilePicDto {
	private String profilePic;
	private List<Chat> userChats;
}
