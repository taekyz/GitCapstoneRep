package com.ktasks.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
	
	public StringToDate(){
		
	}
	
	public Date convert(String string){
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string);
			return date;
		} catch (Exception exception) {
			System.out.print("DEBUG: DATE CONVERT FAILED");
			return null;
		}
	}

}
