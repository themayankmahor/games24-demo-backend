package com.mm.games24.services;

import java.util.List;

import com.mm.games24.payloads.CategoryDto;

public interface CategoryService {
	
	///Create category
	CategoryDto createCategory(CategoryDto categoryDto);
	
	///get all categories
	List<CategoryDto> getAllCategories();
	
	///get single category
	CategoryDto getSingleCategory(int categoryId);
}
