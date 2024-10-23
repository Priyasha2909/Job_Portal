package com.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.code.services.CustomUserDetailsService;
import com.code.util.CustomUserDetails;

@Configuration
public class WebSecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
  //constructor injection
	public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
		
		this.customUserDetailsService = customUserDetailsService;
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
	}

	//urls for which no need for authenication
	 private final String[] publicUrl = {"/",
	            "/global-search/**",
	            "/register",
	            "/register/**",
	            "/webjars/**",
	            "/resources/**",
	            "/assets/**",
	            "/css/**",
	            "/summernote/**",
	            "/js/**",
	            "/*.css",
	            "/*.js",
	            "/*.js.map",
	            "/fonts**", "/favicon.ico", "/resources/**", "/error"};

	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers(publicUrl).permitAll();
			
			//other requests need to authenticate
			auth.anyRequest().authenticated();
		});
		
		//anyone can access login page without actual login
		http.formLogin(form->form.loginPage("/login").permitAll().successHandler(customAuthenticationSuccessHandler)).logout(logout->{
			logout.logoutUrl("/logout");
			logout.logoutSuccessUrl("/");
		}).cors(Customizer.withDefaults())
		.csrf(csrf->csrf.disable());
		
		return http.build();
	}
	
	
	//customer auth provider to tell spring security tofind our users and how to authenticate password
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		
		return authenticationProvider;
		
	}
	
	//custom password encoder: how to authenticate passwords(encryption)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
