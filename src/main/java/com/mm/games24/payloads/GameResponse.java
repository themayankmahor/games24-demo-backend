package com.mm.games24.payloads;

import java.util.List;

import lombok.Data;

@Data
public class GameResponse {

	private List<GameDto> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalElements;
	
	private int totalPage;
	
	private boolean lastPage;
	
}
