package com.mm.games24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.games24.payloads.CategoryDto;
import com.mm.games24.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	///Create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto category = categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}
	
	///Get all categories
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> allCategories = categoryService.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>>(allCategories, HttpStatus.OK);
	}
	
	///Get single category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryId") int categoryId)
	{
		CategoryDto categoryDto = this.categoryService.getSingleCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
}
