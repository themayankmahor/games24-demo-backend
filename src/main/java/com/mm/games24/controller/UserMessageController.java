package com.mm.games24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.games24.payloads.ApiResponse;
import com.mm.games24.payloads.TagDto;
import com.mm.games24.payloads.UserMessageDto;
import com.mm.games24.services.UserMessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user-message/")
public class UserMessageController {

	@Autowired
	private UserMessageService userMessageService;
	
	
	///Create User Message
	@PostMapping("/create-user-message")
	public ResponseEntity<UserMessageDto> createUserMessage(@Valid @RequestBody UserMessageDto userMessageDto)
	{
		UserMessageDto createdUserMessage = this.userMessageService.createUserMessage(userMessageDto);
		
		return new ResponseEntity<UserMessageDto>(createdUserMessage, HttpStatus.CREATED);
	}
	
	///Delete user message
	@DeleteMapping("delete/{userMessageId}")
	public ApiResponse deleteUserMessage(@PathVariable("userMessageId") int userMessageId)
	{
		//delete user message
		userMessageService.deleteUserMessage(userMessageId);
		
		return new ApiResponse("User Message successfully deleted !!!", true);
	}
	
	///Get all messages
	@GetMapping("/")
	public ResponseEntity<List<UserMessageDto>> getAllMessages()
	{
		//
		List<UserMessageDto> allUserMessages = userMessageService.getAllUserMessages();
		
		return new ResponseEntity<List<UserMessageDto>>(allUserMessages, HttpStatus.OK);
	}

}
