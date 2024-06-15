package com.prj.chatapp.serviceUtil;

import java.util.Comparator;

import com.prj.chatapp.dto.UserChatMapDto;

public class SortUserChatMap implements Comparator<UserChatMapDto> {

	@Override
	public int compare(UserChatMapDto u1, UserChatMapDto u2) {
		if(!u1.getChats().isEmpty() && !u2.getChats().isEmpty()){
			if(u1.getChats().get(0).getSentTime().before(u2.getChats().get(0).getSentTime())) {
				return 1;
			}
			else if(u1.getChats().get(0).getSentTime().after(u2.getChats().get(0).getSentTime())) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else if(u1.getChats().isEmpty()) {
			return 1;
		}
		return 0;
	}

}
