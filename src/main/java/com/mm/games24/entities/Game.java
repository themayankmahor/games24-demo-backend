package com.mm.games24.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameId;
	
	private String gameTitle;
	
	@Column(length = 10000)
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
	
	///Data
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tag tag;
}
