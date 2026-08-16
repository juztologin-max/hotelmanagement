package com.hms.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {
	@Bean
	SecurityFilterChain getSecurityFilter(HttpSecurity http) {
		//@formatter:off
		http.authorizeHttpRequests(auth ->
		       auth.requestMatchers("/login","/logout").permitAll()
		       .requestMatchers("/frontpage").authenticated()
		       .requestMatchers("/api/**").authenticated()
		       .requestMatchers("/admin/**").hasRole("ADMIN")    
		       .anyRequest().denyAll())
		    .formLogin(form ->
		       form.loginPage("/login")
		           .defaultSuccessUrl("/frontpage",true));
		//@formatter:on

		return http.build();

	}
	
	@Bean
	BCryptPasswordEncoder getPassWordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
