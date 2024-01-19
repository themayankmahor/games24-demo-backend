package com.mm.games24.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.games24.entities.Category;
import com.mm.games24.exceptions.ResourceNotFoundException;
import com.mm.games24.payloads.CategoryDto;
import com.mm.games24.repository.CategoryRepo;
import com.mm.games24.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//Create Category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		//convert categoryDto to category(obj)
		Category category = this.modelMapper.map(categoryDto, Category.class);
		
		Category savedCategory = this.categoryRepo.save(category);
		
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}
	
	
	///Get all categories
	@Override
	public List<CategoryDto> getAllCategories() {
		//
		List<Category> allCategories = this.categoryRepo.findAll();
		
		List<CategoryDto> result = allCategories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		return result;
	}

	///Get single category
	@Override
	public CategoryDto getSingleCategory(int categoryId) {
		
		//get category obj
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category ID", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	///Delete Category
	@Override
	public void deleteCategory(int categoryId) {
		
		//find category
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category ID", categoryId));
		
		//delete from DB
		categoryRepo.delete(category);
	}

}
