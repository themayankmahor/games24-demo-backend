package com.mm.games24.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
		private int id;
		
		@NotEmpty
		@Size(min = 4, message = "Username must be minimum of 4 characters")
		private String name;
		
		@Email(message = "Email is not valid")
		private String email;
		
		@NotEmpty
		@Size(min = 4, message = "Password must be minimum of 4 characters")
		private String password;
	
}