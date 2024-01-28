package com.mm.games24.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserMessageDto {
	
	private int userMessageId;
	
	@NotEmpty
	@Size(min = 4, message = "Name must be of 4 characters")
	private String name;
	
	@Email
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty
	@Size(min = 4, message = "Message is required")
	private String message;
}
