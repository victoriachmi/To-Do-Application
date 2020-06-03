package com.example.demo.security;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.UserService;

@Configuration
public class IMSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{  
		auth.userDetailsService(new UserService()).passwordEncoder(passwordEncoder());
	}

	@Override 
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
		.antMatchers("/","/login", "/webjars/**")
			.permitAll()
		.antMatchers("/*todo*")
			.hasAnyRole("USER", "ADMIN")
		.antMatchers("/**")
			.hasAnyRole("ADMIN")
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true)
			.failureUrl("/login?error=true")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/login?logout=true")
			.invalidateHttpSession(true)
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/forbiden")
		.and()
			.csrf()
			.disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
			return new UserService();
	}
}
