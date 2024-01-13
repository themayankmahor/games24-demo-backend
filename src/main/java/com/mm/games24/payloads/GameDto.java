package com.mm.games24.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class GameDto {

	private int gameId;
	
	private String gameTitle;
	
	private String description;
	
	private String googlePlayLink;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
}
