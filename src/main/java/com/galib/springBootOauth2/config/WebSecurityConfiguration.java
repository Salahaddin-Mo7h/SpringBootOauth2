package com.galib.springBootOauth2.config;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.core.Ordered;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	 @Autowired
	 private UserDetailsService userDetailsService;

	    @Bean
	    protected AuthenticationManager getAuthenticationManager() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }
	    

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

			http
			.authorizeRequests().antMatchers("/oauth/token").permitAll()
			.antMatchers("/oauth/token/logout").permitAll()
			.antMatchers("/tokens/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().csrf().disable()
			.cors().configurationSource(corsConfigurationSource());
	    }

	    //This can be customized as required
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        List<String> allowOrigins = Arrays.asList("*");
	        configuration.setAllowedOrigins(allowOrigins);
	        configuration.setAllowedMethods(Collections.singletonList("*"));
	        configuration.setAllowedHeaders(Collections.singletonList("*"));
	        //in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
	        configuration.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
}
