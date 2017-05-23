package com.example.wjx.xing.utils;

import java.text.DateFormat;
import java.util.Date;

public class DateUtil {
	public static String getCurrentTime() {
		return String.valueOf(System.currentTimeMillis());
	}
	public static boolean isOneDay(String time1,String time2) {
		DateFormat format=DateFormat.getDateInstance();
		Date date1=new Date(Long.parseLong(time1));
		Date date2=new Date(Long.parseLong(time2));
		if (date1.getYear()!=date2.getYear()) {
			return false;
		}else if(date1.getMonth()!=date2.getMonth()){
			return false;
		}else if (date1.getDay()!=date2.getDay()) {
			return false;
		}
		return true;
	}
	
}
