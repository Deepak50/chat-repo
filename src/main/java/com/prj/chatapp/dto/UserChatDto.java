package com.prj.chatapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChatDto {
	private Long chatId;
	private String chatDesc;
	private Date deliveredTime;
	private Date seenTime;
	private Date sentTime;
	private String fromUserId;
	private String toUserId;
}
