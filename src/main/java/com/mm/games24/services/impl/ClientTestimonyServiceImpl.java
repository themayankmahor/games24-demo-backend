package com.mm.games24.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.games24.entities.ClientTestimony;
import com.mm.games24.payloads.ClientTestimonyDto;
import com.mm.games24.payloads.GameDto;
import com.mm.games24.repository.ClientTestimonyRepo;
import com.mm.games24.services.ClientTestimonyService;

@Service
public class ClientTestimonyServiceImpl implements ClientTestimonyService{

	@Autowired
	private ClientTestimonyRepo clientTestimonyRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ClientTestimonyDto createClient(ClientTestimonyDto clientTestimonyDto) {
		
		ClientTestimony clientTestimony = this.modelMapper.map(clientTestimonyDto, ClientTestimony.class);
		
		//save to database
		ClientTestimony savedClient = this.clientTestimonyRepo.save(clientTestimony);
		
		return this.modelMapper.map(savedClient, ClientTestimonyDto.class);
	}
	
	///Get all clients
	@Override
	public List<ClientTestimonyDto> getAllTestimony() {
		
		List<ClientTestimony> allClients = this.clientTestimonyRepo.findAll();
		
		List<ClientTestimonyDto> allDtos = allClients.stream().map((client) -> modelMapper.map(client, ClientTestimonyDto.class)).collect(Collectors.toList());
		
		return allDtos;
	}
	
	///Create multiple client DTOs
	@Override
	public List<ClientTestimonyDto> createMultipleClients(List<ClientTestimonyDto> clientTestimonyDtos) {
		
		List<ClientTestimony> clientList = clientTestimonyDtos.stream().map((client) -> modelMapper.map(client, ClientTestimony.class)).collect(Collectors.toList());
		
		///save to database
		clientList.stream().forEach((client) -> {
			
			if(client.getClientName() != null && !client.getClientName().trim().isEmpty())
			{
				clientTestimonyRepo.save(client);
			}
		});
		
		List<ClientTestimonyDto> clientDtosList = clientList.stream().map((client) -> modelMapper.map(client, ClientTestimonyDto.class)).collect(Collectors.toList());
		
		return clientDtosList;
		
	}

		
	
}
