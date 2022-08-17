package com.galib.springBootOauth2.config;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.galib.springBootOauth2.users.Users;

public class AuthUserDetail extends Users implements UserDetails{

	public AuthUserDetail(Users users) {
        super(users);
    }


	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {

	        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	        getRoles().forEach(role -> {
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	            role.getPermissions().forEach(permission -> {
//	                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
	            });

	        });
	        return grantedAuthorities;
	    }

	    @Override
	    public String getPassword() {
	        return super.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return super.getUsername();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return super.isAccountNonExpired();
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return super.isAccountNonLocked();
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return super.isCredentialsNonExpired();
	    }

	    @Override
	    public boolean isEnabled() {
	        return super.isEnabled();
	    }
}
