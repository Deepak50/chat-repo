package com.prj.chatapp.serviceUtil;

import java.util.Comparator;
import com.prj.chatapp.entity.Chat;

public class SortChat implements Comparator<Chat> {

	@Override
	public int compare(Chat c1, Chat c2) {
		if(c1.getSentTime().before(c2.getSentTime())) {
			return 1;
		}
		else if(c1.getSentTime().after(c2.getSentTime())) {
			return -1;
		}
		return 0;
	}
	
}
