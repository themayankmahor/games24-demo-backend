package com.mm.games24.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.games24.entities.User;
import com.mm.games24.exceptions.ApiException;
import com.mm.games24.payloads.JwtAuthRequest;
import com.mm.games24.payloads.JwtAuthResponse;
import com.mm.games24.payloads.UserDto;
import com.mm.games24.security.JWTTokenHelper;
import com.mm.games24.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	///Authenticate
	public void authenticate(String username, String password) throws Exception
	{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			
			authenticationManager.authenticate(authenticationToken);
			
		} catch (Exception e) {
			//
			System.out.println("Invalid Details");
			
			throw new ApiException("Invalid Username or Password");
		}
	}
	
	///Register new user
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto registeredUser = this.userService.registerNewuser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}
	
	///Login user
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
	{
		authenticate(request.getUsername(), request.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		
		response.setToken(token);
		response.setUser(this.modelMapper.map((User)userDetails, UserDto.class));
		
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}
	
}
