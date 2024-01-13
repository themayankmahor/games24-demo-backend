package com.mm.games24.services;

import java.util.List;

import com.mm.games24.payloads.GameDto;
import com.mm.games24.payloads.GameResponse;

public interface GameService {

	///Create Game
	GameDto createGame(GameDto gameDto, int categroyId);
	
	///update game
	GameDto updateGame(GameDto gameDto, int gameId);
	
	///get all game
	GameResponse getAllGame(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	///get single game
	GameDto getGameById(int gameId);
	
	///Get single game by category
	List<GameDto> getGameByCategory(int categoryId);
	
	///delete game
	void deleteGame(int gameId);
}
