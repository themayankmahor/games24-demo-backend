package com.mm.games24.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.games24.entities.Category;
import com.mm.games24.entities.Game;

public interface GameRepo extends JpaRepository<Game, Integer>{

	List<Game> findByCategory(Category category);
	
}
