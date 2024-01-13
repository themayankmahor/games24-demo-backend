package com.mm.games24.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Role {

	@Id
	private int roleId;
	
	private String name;
}
