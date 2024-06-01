package com.prj.chatapp.dto;

import java.util.Date;

import com.prj.chatapp.entity.Userr;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
	private String chatId;
	private Userr fromUser;
	private Userr toUser;
	@Lob
	private String chatDesc;
	private Date sentTime;
	private Date deliveredTime;
	private Date seenTime;
}
