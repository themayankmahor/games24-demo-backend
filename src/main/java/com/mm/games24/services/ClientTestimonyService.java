package com.mm.games24.services;

import java.util.List;

import com.mm.games24.payloads.ClientTestimonyDto;

public interface ClientTestimonyService {

	///create client
	ClientTestimonyDto createClient(ClientTestimonyDto clientTestimonyDto);
	
	///get all client
	List<ClientTestimonyDto> getAllTestimony();
}
