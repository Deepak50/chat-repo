package com.prj.chatapp.serviceUtil;

import java.util.Date;


//more or less useless piece of code. could have been done in the same place. But avoids confusion
public class DateUtil {
	public static Date recentDate(Object dateObj1, Object dateObj2) {
		
		if(dateObj1 == null) return (Date)dateObj2;
		else if(dateObj2 == null) return (Date)dateObj1;
		
		Date date1 = (Date)dateObj1;
		Date date2 = (Date)dateObj2;

		if(date1.after(date2)) return date1;
		return date2;
	}
}
