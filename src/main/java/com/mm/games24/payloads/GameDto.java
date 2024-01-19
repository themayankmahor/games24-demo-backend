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
	
	///Links
	private String googlePlayLink;
	
	private String appleStoreLink;
	
	private String steamLink;
	
	///Images
	private String bannerImage;
	
	private String squareImage;
	
	///Screen shots
	private String screenShot1;
	
	private String screenShot2;
	
	private String screenShot3;
	
	private String screenShot4;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private TagDto tag;
	
}
