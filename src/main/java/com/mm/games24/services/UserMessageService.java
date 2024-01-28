package com.mm.games24.services;

import java.util.List;

import com.mm.games24.payloads.UserMessageDto;

public interface UserMessageService {

	///Create message
	UserMessageDto createUserMessage(UserMessageDto userMessageDto);
	
	///get all messages
	List<UserMessageDto> getAllUserMessages();
	
	///delete user
	void deleteUserMessage(int userMessageId);
}
