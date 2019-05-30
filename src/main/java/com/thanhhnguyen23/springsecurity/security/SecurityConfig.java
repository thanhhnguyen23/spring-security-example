package com.thanhhnguyen23.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
/*
 * WebSecurityConfigurerAdapter - setup web security configuration for the authorization server 
 * 		> enables HTTP security
 * 		> provides default configuration in the configure(HttpSecurith http) method
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/*
	 * AuthenticationManagerBuilder is used to build in memory authentication, LDAP authentication, JDBC based authentication, adding UserDetailService, and adding AuthenticationProviders
	 */
	// authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("user1").password(
				passwordEncoder().encode("secret1"))
		.roles("USER")
		.and()
		.withUser("admin1").password(
				passwordEncoder().encode("secret1"))
		.roles("USER", "ADMIN");
	}
	// authorization : Role --> Access
	/*
	 * HttpSecurity is used to authorize each HTTP request 
	 */
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic().and().authorizeRequests().antMatchers("/students/**")
		.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and()
		.csrf().disable().headers().frameOptions().disable();
	}
	
	// registering passwordEncoder as BCryptPasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
