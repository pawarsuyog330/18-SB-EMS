package com.ashokit.ems.userdetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailsService implements UserDetailsService {
	

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Entered into MyUserDetailsService#loadUserByUsername");
		return User.withUsername("Juli")
				   .password(encoder.encode("juli@34"))
				   .authorities("ROLE_MANAGER")
				   .build();
	
	}

}
