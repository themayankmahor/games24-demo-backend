package com.mm.games24.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userMessageId;
	
	private String name;
	
	private String email;
	
	@Column(length = 10000)
	private String message;
	
}
