package com.galib.springBootOauth2.config;

public class CustomOauthMessage {

	public static String customMessage(String mgs) {
		
		if(mgs.startsWith("Bad credentials")) {
		
			mgs = "Invalid username or password";
		 }
		
	   return mgs;
	}
}