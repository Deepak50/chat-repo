package com.prj.chatapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentChatWithUserName {
	private String userId;
	private String userName;
	private Date sentDate;
}
