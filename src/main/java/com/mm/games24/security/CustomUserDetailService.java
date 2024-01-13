package com.mm.games24.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mm.games24.entities.User;
import com.mm.games24.exceptions.ResourceNotFoundException;
import com.mm.games24.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;

	///Load user by username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from db by username
		User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email :" + username, 0));
		
		
		return user;
	}
	
}
