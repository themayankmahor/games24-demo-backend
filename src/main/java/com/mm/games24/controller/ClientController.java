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
import com.mm.games24.payloads.ClientTestimonyDto;
import com.mm.games24.services.ClientTestimonyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

	@Autowired
	private ClientTestimonyService clientTestimonyService;
	
	///Create new client
	@PostMapping("/create-client")
	ResponseEntity<ClientTestimonyDto> createClient(@Valid @RequestBody ClientTestimonyDto clientTestimonyDto)
	{
		ClientTestimonyDto createdClient = this.clientTestimonyService.createClient(clientTestimonyDto);
		
		return new ResponseEntity<ClientTestimonyDto>(createdClient, HttpStatus.CREATED);
	}
	
	///Create multiple clients
	@PostMapping("/create-clients")
	ResponseEntity<List<ClientTestimonyDto>> createMultipleClients(@Valid @RequestBody List<ClientTestimonyDto> clientTestimonyDtos)
	{
		List<ClientTestimonyDto> createdClients = clientTestimonyService.createMultipleClients(clientTestimonyDtos);
		
		return new ResponseEntity<List<ClientTestimonyDto>>(createdClients, HttpStatus.CREATED);
	}
	
	///Get all clients
	@GetMapping("/")
	ResponseEntity<List<ClientTestimonyDto>> getAllClients()
	{
		List<ClientTestimonyDto> allClients = this.clientTestimonyService.getAllTestimony();
		
		return new ResponseEntity<List<ClientTestimonyDto>>(allClients, HttpStatus.OK);
	}
	
	///Client Testimony delete
	@DeleteMapping("/delete/{clientTestimonyId}")
	public ApiResponse deleteClient(@PathVariable("clientTestimonyId") int clientTestimonyId)
	{
		///delete
		clientTestimonyService.deleteClientTestimony(clientTestimonyId);
		
		return new ApiResponse("Client Testimony successfully deleted !!!", true);
	}
}
