package com.code.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.code.entities.Users;
import com.code.repositories.UsersRepository;
import com.code.util.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   
	private UsersRepository usersRepository;
	
	

	public CustomUserDetailsService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Users user = usersRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Could not found the user"));
		return new CustomUserDetails(user);
	}
}
