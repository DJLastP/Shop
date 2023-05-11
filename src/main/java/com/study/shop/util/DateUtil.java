package com.study.shop.util;

import java.util.Calendar;

public class DateUtil {
	
	//오늘 날짜를 문자열로 리턴
	public static String getNowDateToString() {
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); //년도 2023
		int month = cal.get(Calendar.MONTH) + 1; //월 3
		int date = cal.get(Calendar.DATE); //일 12

		return year + "-" + String.format("%02d", month)  + "-" + String.format("%02d", date);
	}
	
	public static String getNowDateToString(String seperator) {
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); //년도 2023
		int month = cal.get(Calendar.MONTH) + 1; //월 3
		int date = cal.get(Calendar.DATE); //일 12

		return year + seperator + String.format("%02d", month)  + seperator + String.format("%02d", date);
	}
	
	public static int getNowYearToInt() {
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); //년도 2023
		
		return year;
	}
	
	
	
	//이번달의 첫 날짜
	public static String getFirstDateOfMonth() {
		return getNowDateToString().substring(0, 8) + "01";
	}
}
