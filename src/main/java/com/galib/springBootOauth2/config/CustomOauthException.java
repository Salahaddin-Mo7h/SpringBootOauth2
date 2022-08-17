package com.galib.springBootOauth2.config;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;


@SuppressWarnings("serial")
public class CustomOauthException extends OAuth2Exception {
   
	public CustomOauthException(String msg) {
       super(msg);
    }
}