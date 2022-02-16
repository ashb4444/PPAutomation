package com.primepenguin.poc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String convertDateToString(String dformat, Date date) {
		DateFormat dateFormat = new SimpleDateFormat(dformat);
		return dateFormat.format(date).toString();
	}
	
	public static Date convertStringToDate(String dformat, String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(dformat);
		return dateFormat.parse(date);
	}
	
	public static void main(String[] args) {
		try {
			String date = "01.01.2010";
			System.out.println(convertStringToDate("ddMMyyyy",date.replace(".", "")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
