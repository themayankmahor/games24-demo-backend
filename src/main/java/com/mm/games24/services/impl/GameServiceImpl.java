package com.mm.games24.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mm.games24.entities.Category;
import com.mm.games24.entities.Game;
import com.mm.games24.entities.Tag;
import com.mm.games24.exceptions.ResourceNotFoundException;
import com.mm.games24.payloads.GameDto;
import com.mm.games24.payloads.GameResponse;
import com.mm.games24.repository.CategoryRepo;
import com.mm.games24.repository.GameRepo;
import com.mm.games24.repository.TagRepo;
import com.mm.games24.services.FileService;
import com.mm.games24.services.GameService;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	private GameRepo gameRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	///Create Game
	@Override
	public GameDto createGame(GameDto gameDto, int categoryId, int tagId) {
		
		//get category  by ID
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", " category ID ", categoryId));
		
		//get Tag by ID
		Tag tag = this.tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag ", "tag ID", tagId));
		
		///Convert game dto to game (obj)
		Game game = this.modelMapper.map(gameDto, Game.class);
		
		game.setAddedDate(new Date());
//		game.setBannerImage("default.png");		
//		game.setSquareImage("default.png");		
//		game.setScreenShot1("default.png");		
//		game.setScreenShot2("default.png");		
//		game.setScreenShot3("default.png");		
//		game.setScreenShot4("default.png");
		game.setTag(tag);
		game.setCategory(category);
		
		//save game (post) to database
		Game savedGame = this.gameRepo.save(game);
		
		return this.modelMapper.map(savedGame, GameDto.class);
	}
	
	///Create Multiple Games
	@Override
	public List<GameDto> createMultipleGames(List<GameDto> gameDtos) {
		
		///convert dtos to entity objs
		List<Game> games = gameDtos.stream().map((game) -> modelMapper.map(game, Game.class)).collect(Collectors.toList());
		
		games.stream().forEach((game) -> {
			
			gameRepo.save(game);		
		});
		
		return games.stream().map((game) -> modelMapper.map(game, GameDto.class)).collect(Collectors.toList());
	}
	
	///Get all games
	@Override
	public GameResponse getAllGame(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		//Sort
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		//
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Game> pageGame = gameRepo.findAll(p);
		List<Game> allGame = pageGame.getContent();
		
		List<GameDto> gameDtos = allGame.stream().map((game) -> modelMapper.map(game, GameDto.class)).collect(Collectors.toList());
		
		GameResponse gameResponse = new GameResponse();
		
		gameResponse.setContent(gameDtos);
		gameResponse.setPageNumber(pageGame.getNumber());
		gameResponse.setPageSize(pageGame.getSize());
		gameResponse.setTotalElements(pageGame.getTotalElements());
		gameResponse.setTotalPage(pageGame.getTotalPages());
		gameResponse.setLastPage(pageGame.isLast());
		
		return gameResponse;
	}
	
	
	///Update Game
	@Override
	public GameDto updateGame(GameDto gameDto, int gameId) {
		
		//find game by game ID
		Game game = this.gameRepo.findById(gameId).orElseThrow(() -> new ResourceNotFoundException("Game ", " game id ", gameId));
		
		//get category from gameDTO
		Category category = this.categoryRepo.findById(gameDto.getCategory().getCategoryId()).get();
		
		//update game data
		game.setGameTitle(gameDto.getGameTitle());
		game.setBannerImage(gameDto.getBannerImage());
		game.setSquareImage(gameDto.getSquareImage());
		game.setScreenShot1(gameDto.getScreenShot1());
		game.setScreenShot2(gameDto.getScreenShot2());
		game.setScreenShot3(gameDto.getScreenShot3());
		game.setScreenShot4(gameDto.getScreenShot4());
		game.setDescription(gameDto.getDescription());
		game.setGooglePlayLink(gameDto.getGooglePlayLink());
		game.setCategory(category);
		
		Game savedGame = this.gameRepo.save(game);
		
		return this.modelMapper.map(savedGame, GameDto.class);
	}
	
	
	///Get game by gameID
	@Override
	public GameDto getGameById(int gameId) {
		
		Game game = this.gameRepo.findById(gameId).orElseThrow(() -> new ResourceNotFoundException("Game ", " game ID ", gameId));
		
		return this.modelMapper.map(game, GameDto.class);
		
	}

	///Get games by category ID 
	@Override
	public List<GameDto> getGameByCategory(int categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categroy ID :", categoryId));
		
		List<Game> games = this.gameRepo.findByCategory(category);
		
		List<GameDto> gameDtos = games.stream().map((game) -> this.modelMapper.map(game, GameDto.class)).collect(Collectors.toList());
		
		return gameDtos;
	}
	
	
	///Delete game
	@Override
	public void deleteGame(int gameId) {
		
		Game game = this.gameRepo.findById(gameId).orElseThrow(() -> new ResourceNotFoundException("Game ", " game ID ", gameId));
		
		//delete game
		this.gameRepo.delete(game);
	}



}
