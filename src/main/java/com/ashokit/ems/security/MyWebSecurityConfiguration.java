package com.ashokit.ems.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	BCryptPasswordEncoder  encoder;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		//.antMatchers("/index**")
		//.permitAll()
		.antMatchers("/list**", "/index**", "/add**","/edit**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
		.antMatchers("/delete**")
	    .hasAuthority("ROLE_MANAGER")
		.and()
	//	.httpBasic()
		.formLogin()
		/*
		.loginPage("") //fill your login page
		.usernameParameter("").passwordParameter("") //fill username and password parameters
		.loginProcessingUrl("") //fill form action
		.failureUrl("") //fill redirection url
		*/
		.and()
		.exceptionHandling()
		.accessDeniedPage("/WEB-INF/views/noAccessToYou.jsp");
		
	//	http.logout(); //It initiates the logout of user at /logout url and
		               // redirects the user to /login url
		
	//	http.logout().logoutUrl("/logoutMe").logoutSuccessUrl("/userLogout").permitAll();
		http.logout().logoutUrl("/logoutMe");
		
		//session management
		http.sessionManagement().maximumSessions(1);
		
		//enable https
		http.requiresChannel().anyRequest().requiresSecure();
	}
	
	/*
	@Bean
	public DaoAuthenticationProvider provider1() {
		DaoAuthenticationProvider p1 = new DaoAuthenticationProvider();
		p1.setUserDetailsService(new MyUserDetailsService());
		p1.setPasswordEncoder(encoder);
		return p1;
	}
	
	@Bean
	public DaoAuthenticationProvider provider2() {
		DaoAuthenticationProvider p1 = new DaoAuthenticationProvider();
		p1.setUserDetailsService(new MyUserDetailsService2());
		p1.setPasswordEncoder(encoder);
		return p1;
	}
	*/
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth .inMemoryAuthentication()
		 * .withUser("tanvi").password(encoder.encode("allen#21")).roles("ADMIN") .and()
		 * .withUser("suyog").password(encoder.encode("juli@34")).roles("MANAGER");
		 */
		
		//auth.userDetailsService(new MyUserDetailsService()).passwordEncoder(encoder);
		
		
//		auth.authenticationProvider(provider1())
//		    .authenticationProvider(provider2());
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select username, authority  from authorities where username=?")
		.passwordEncoder(encoder);
		
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
