package com.galib.springBootOauth2.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	
	@Override
	  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	        resources.resourceId(null);
	  }
	
	
	@Override
    public void configure(final HttpSecurity http) throws Exception {
		
        http
        	.headers()
        	.and()
            .authorizeRequests()
            .antMatchers("/oauth/token").permitAll()
//            .antMatchers("/acl/**").hasAnyRole("ADMIN")
    		.anyRequest().authenticated()
    		.and().logout().and()
    		.csrf().disable();
                
            }
	
	 
}
