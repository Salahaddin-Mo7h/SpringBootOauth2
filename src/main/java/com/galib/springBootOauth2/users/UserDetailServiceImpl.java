package com.galib.springBootOauth2.users;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.galib.springBootOauth2.config.AuthUserDetail;

//import com.jivita.rammps.callerInfo.CallersRepository;
//import com.jivita.rammps.config.AuthUserDetail;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<Users> optionalUser = userDetailRepository.findByUsername(name);
        
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;

    }
}
