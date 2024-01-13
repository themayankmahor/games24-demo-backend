package com.mm.games24.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mm.games24.config.AppConstants;
import com.mm.games24.entities.Role;
import com.mm.games24.entities.User;
import com.mm.games24.payloads.UserDto;
import com.mm.games24.repository.RoleRepo;
import com.mm.games24.repository.UserRepo;
import com.mm.games24.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	///Create User
	@Override
	public UserDto createUser(UserDto userDto) {
		
		///convert userdto to user
		User user = this.modelMapper.map(userDto, User.class);
		
		//save converted user to database
		User savedUser = this.userRepo.save(user);
		
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	///Register New user
	@Override
	public UserDto registerNewuser(UserDto userDto) {
		
		///convert dto to obj
		User user = this.modelMapper.map(userDto, User.class);
		
		//encoded the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//set roles to user
		Role role = this.roleRepo.findById(AppConstants.ADMIN_USER).get();
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
