package com.mm.games24.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mm.games24.config.AppConstants;
import com.mm.games24.payloads.ApiResponse;
import com.mm.games24.payloads.CategoryDto;
import com.mm.games24.payloads.GameDto;
import com.mm.games24.payloads.GameResponse;
import com.mm.games24.services.FileService;
import com.mm.games24.services.GameService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	///Add Game
	@PostMapping("/category/{categoryId}/tag/{tagId}")
	public ResponseEntity<GameDto> createGame(@Valid @RequestBody GameDto gameDto, @PathVariable("categoryId") int categoryId, @PathVariable("tagId") int tagId)
	{
		GameDto savedGame = this.gameService.createGame(gameDto, categoryId, tagId);
		
		return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
	}
	
	///Add multiple Games
//	@PostMapping("/add-games")
//	public ResponseEntity<List<GameDto>> addMultipleGames(@Valid @RequestBody List<GameDto> gameDtos)
//	{
//		for(int gameDto = 0; gameDto < gameDtos.size(); gameDto++)
//		{
//			GameDto game = gameDtos.get(gameDto);
//			int categoryId = gameDtos.get(gameDto).getCategory().getCategoryId();
//			
//			this.gameService.createGame(game, categoryId);	
//		}
//		
//		return new ResponseEntity<List<GameDto>>(gameDtos, HttpStatus.CREATED);
//	}
//	
	///Get all games
	@GetMapping("/")
	public ResponseEntity<GameResponse> getAllGame(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
			)
	{
		GameResponse gameResponse = gameService.getAllGame(pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<GameResponse>(gameResponse, HttpStatus.OK);
	}
	
	///Get multiple Games
	@GetMapping("/get-all-games/")
	public ResponseEntity<List<GameDto>> getEveryGame()
	{
		List<GameDto> allGames = gameService.getEveryGame();
		
		return new ResponseEntity<List<GameDto>>(allGames, HttpStatus.OK);
	}
	
	///Get game by ID
	@GetMapping("/{gameId}")
	public ResponseEntity<GameDto> getGameById(@PathVariable("gameId") int gameId)
	{
		GameDto gameDto = this.gameService.getGameById(gameId);
		
		return new ResponseEntity<GameDto>(gameDto, HttpStatus.OK);
	}
	
	///Get game by category
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<GameDto>> getGamesByCategory(@PathVariable("categoryId") int categoryId)
	{
		List<GameDto> games = this.gameService.getGameByCategory(categoryId);
		
		return new ResponseEntity<List<GameDto>>(games, HttpStatus.OK);
	}
	
	///Update Game
	@PutMapping("/update/{gameId}")
	public ResponseEntity<GameDto> updatePost(@RequestBody GameDto gameDto, @PathVariable("gameId") int gameId)
	{
		GameDto updatedGame = this.gameService.updateGame(gameDto, gameId);
		
		return new ResponseEntity<GameDto>(updatedGame, HttpStatus.OK);
	}
	
	///Delete game
	@DeleteMapping("/{gameId}")
	public ApiResponse deleteGame(@PathVariable("gameId") int gameId)
	{
		this.gameService.deleteGame(gameId);
		
		return new ApiResponse("Game successfully deleted !!!", true);
	}
	
	///Upload Banner Image
	@PostMapping("/image/upload-banner-image/{gameId}")
	public ResponseEntity<GameDto> uploadGameBannerImage(@RequestParam("image") MultipartFile image,
			@PathVariable("gameId") int gameId
			) throws IOException
	{
		GameDto gameDto = gameService.getGameById(gameId);
		
		String fileName = fileService.uploadImage(path, image);
		
		gameDto.setBannerImage(fileName);
		GameDto updatedGame = gameService.updateGame(gameDto, gameId);
		
		return new ResponseEntity<GameDto>(updatedGame, HttpStatus.OK);
	}
	
	///Upload Square Image
	@PostMapping("/image/upload-square-image/{gameId}")
	public ResponseEntity<GameDto> uploadGameSquareImage(@RequestParam MultipartFile image,
			@PathVariable("gameId") int gameId
			) throws IOException
	{
		GameDto gameDto = gameService.getGameById(gameId);
		
		String fileName = fileService.uploadImage(path, image);
		
		gameDto.setSquareImage(fileName);
		GameDto updatedGame = gameService.updateGame(gameDto, gameId);
		
		return new ResponseEntity<GameDto>(updatedGame, HttpStatus.OK);
	}
	
	///Upload Screen shot1 Image
	@PostMapping("/image/upload-ss1-image/{gameId}")
	public ResponseEntity<GameDto> uploadGameScreenShot1Image(@RequestParam MultipartFile image,
			@PathVariable("gameId") int gameId
			) throws IOException
	{
		GameDto gameDto = gameService.getGameById(gameId);
		
		String fileName = fileService.uploadImage(path, image);
		
		gameDto.setScreenShot1(fileName);
		GameDto updatedGame = gameService.updateGame(gameDto, gameId);
		
		return new ResponseEntity<GameDto>(updatedGame, HttpStatus.OK);
	}
	
	///Upload Screen shot2 Image
	@PostMapping("/image/upload-ss2-image/{gameId}")
	public ResponseEntity<GameDto> uploadGameScreenShot2Image(@RequestParam MultipartFile image,
			@PathVariable("gameId") int gameId
			) throws IOException
	{
		GameDto gameDto = gameService.getGameById(gameId);
		
		String fileName = fileService.uploadImage(path, image);
		
		gameDto.setScreenShot2(fileName);
		GameDto updatedGame = gameService.updateGame(gameDto, gameId);
		
		return new ResponseEntity<GameDto>(updatedGame, HttpStatus.OK);
	}
	
	///Upload Screen shot3 Image
	@PostMapping("/image/upload-ss3-image/{gameId}")
	public ResponseEntity<GameDto> uploadGameScreenShot3Image(@RequestParam MultipartFile image,
			@PathVariable("gameId") int gameId
			) throws IOException
	{
		GameDto gameDto = gameService.getGameById(gameId);
		
		String fileName = fileService.uploadImage(path, image);
		
		gameDto.setScreenShot3(fileName);
		GameDto updatedGame = gameService.updateGame(gameDto, gameId);
		
		return new ResponseEntity<GameDto>(updatedGame, HttpStatus.OK);
	}
	
	///Upload Screen shot4 Image
	@PostMapping("/image/upload-ss4-image/{gameId}")
	public ResponseEntity<GameDto> uploadGameScreenShot4Image(@RequestParam MultipartFile image,
			@PathVariable("gameId") int gameId
			) throws IOException
	{
		GameDto gameDto = gameService.getGameById(gameId);
		
		String fileName = fileService.uploadImage(path, image);
		
		gameDto.setScreenShot4(fileName);
		GameDto updatedGame = gameService.updateGame(gameDto, gameId);
		
		return new ResponseEntity<GameDto>(updatedGame, HttpStatus.OK);
	}
	///get resource (IMAGE)
	@GetMapping(value = "image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
	{
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
