package com.galib.springBootOauth2.sessains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Repository;

//import com.jivita.rammps.config.*;

@Repository
public interface SessionRepository extends JpaRepository<Session,Integer>{

	 Session findByAccessToken(String oAuth2AccessToken);

}
