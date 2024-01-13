package com.mm.games24.services;

import com.mm.games24.payloads.UserDto;

public interface UserService {

	///Create User
	UserDto createUser(UserDto user);
	
	///Register New user
	UserDto registerNewuser(UserDto userDto);
	
}
