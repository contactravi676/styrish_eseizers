package com.styrish.comman;

import java.sql.Date;
import java.sql.Timestamp;

public class CommanUtility {
	
	public static Timestamp getCurrentDate() {
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());	
		return date;
	}

}
