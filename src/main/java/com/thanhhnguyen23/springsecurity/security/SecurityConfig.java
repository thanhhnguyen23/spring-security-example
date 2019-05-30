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
//		.passwordEncoder(org.springframework
//				.security
//				.crypto
//				.password
//				/*
//				 * NoOpPasswordEncoder is deprecated
//				 * 		> This PasswordEncoder is not secure. 
//				 * 		> Instead use an adaptive one way function like 
//				 * 			* BCryptPasswordEncoder, 
//				 * 			* Pbkdf2PasswordEncoder, or 
//				 * 			* SCryptPasswordEncoder. 
//				 * 		> Even better use DelegatingPasswordEncoder which supports password upgrades.
//				 */
//				//TODO - find alternative since NoOpPasswordEncoder is deprecated
//				.NoOpPasswordEncoder
//				.getInstance())
		.withUser("user1").password(
//				"secret1")
				passwordEncoder().encode("secret1"))
		.roles("USER")
		.and()
		.withUser("admin1").password(
//				"secret1")
				passwordEncoder().encode("secret1"))
		.roles("USER", "ADMIN");
	}
	// authorization : Role --> Access
	/*
	 * HttpSecurity is used
	 */
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic().and().authorizeRequests().antMatchers("/students/**")
		.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and()
		.csrf().disable().headers().frameOptions().disable();

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
