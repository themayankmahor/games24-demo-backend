package com.mm.games24.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.games24.entities.UserMessage;
import com.mm.games24.exceptions.ResourceNotFoundException;
import com.mm.games24.payloads.UserMessageDto;
import com.mm.games24.repository.UserMessageRepo;
import com.mm.games24.services.UserMessageService;

@Service
public class UserMessageServiceImpl implements UserMessageService{
	
	@Autowired
	private UserMessageRepo userMessageRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	///Create user message
	@Override
	public UserMessageDto createUserMessage(UserMessageDto userMessageDto) {
		
		UserMessage userMessage = this.modelMapper.map(userMessageDto, UserMessage.class);
		
		//save to database
		UserMessage savedUserMessage = userMessageRepo.save(userMessage);
		
		return this.modelMapper.map(savedUserMessage, UserMessageDto.class);
	}

	///delete user message
	@Override
	public void deleteUserMessage(int userMessageId) {
		
		UserMessage userMessage = this.userMessageRepo.findById(userMessageId).orElseThrow(() -> new ResourceNotFoundException("User message", "user message ID", userMessageId));

		//delete from database
		this.userMessageRepo.delete(userMessage);
	}

	///Get all messages
	@Override
	public List<UserMessageDto> getAllUserMessages() {
		
		List<UserMessage> allMessages = this.userMessageRepo.findAll();
		
		return allMessages.stream().map((message) -> this.modelMapper.map(message, UserMessageDto.class)).collect(Collectors.toList());
	}

}
