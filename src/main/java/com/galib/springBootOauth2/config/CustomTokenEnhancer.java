package com.galib.springBootOauth2.config;

import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.galib.springBootOauth2.sessains.Session;
import com.galib.springBootOauth2.sessains.SessionRepository;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

public class CustomTokenEnhancer implements TokenEnhancer {
	 
	@Autowired
	SessionRepository SessionRepositor;
	  
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        
   	final Map<String, Object> additionalInfo = new HashMap<String, Object>();
        
   	AuthUserDetail myUserDetails = (AuthUserDetail)authentication.getPrincipal();

   	Session session = new Session();
   	
   	session.setAccessToken(accessToken.toString());
//   	session.setUserId(myUserDetails.getId());
   	session.setUserName(myUserDetails.getUsername());
   	session.setLoginTime(new Date());
   	
   	session = SessionRepositor.save(session);
   	
   	System.out.println("Sesssion obj : >> "+session.toString());

        additionalInfo.put("name", authentication.getName());
        
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
       
//        userAccessLog(myUserDetails.getUserId(), myUserDetails.getOrganizationNo(), myUserDetails.getCompanyNo(), clientIpAdress,sessionNo,osName,browserName,macAdress);
       System.out.println(myUserDetails+"====== save login info to LOGIN in TABLE ============= : accessToken: "+accessToken);
        return accessToken;
    }
    
    
}
