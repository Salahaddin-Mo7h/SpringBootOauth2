package com.galib.springBootOauth2.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@Configuration
@EnableAuthorizationServer
@Import(WebSecurityConfiguration.class)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private DataSource dataSource;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private TokenStore tokenStore;

	    @Bean
	    TokenStore jdbcTokenStore() {
	        return new JdbcTokenStore(dataSource);
	    }

	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	    	security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(passwordEncoder);

	    }

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);

	    }

	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
	         endpoints.tokenStore(tokenStore)
	         		 .tokenEnhancer(tokenEnhancer())
	         		 .tokenEnhancer(tokenEnhancerChain)
	         		 .authenticationManager(authenticationManager)
	         		 .exceptionTranslator(new MyWebResponseExceptionTranslator());
	    }
	    
	    @Bean
	    public DefaultTokenServices tokenServices() {
	    	
	        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore);
	        defaultTokenServices.setSupportRefreshToken(true);
	        defaultTokenServices.setAuthenticationManager(authenticationManager);
	        defaultTokenServices.setTokenEnhancer(tokenEnhancer());
	       
	        return defaultTokenServices;
	    }
	     
	    
	    @Bean
	    public TokenEnhancer tokenEnhancer() {
			return new CustomTokenEnhancer();
	        
	    }
}