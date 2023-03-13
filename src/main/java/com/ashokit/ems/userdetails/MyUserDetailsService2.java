package com.ashokit.ems.userdetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailsService2 implements UserDetailsService {
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService2.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Entered into MyUserDetailsService2#loadUserByUsername");
		return User.withUsername("Allen")
				   .password(encoder.encode("allen#21"))
				   .authorities("ROLE_ADMIN")
				   .build();
	
	}

}


