package com.mm.games24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.games24.payloads.ApiResponse;
import com.mm.games24.payloads.TagDto;
import com.mm.games24.services.TagService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
	
	@Autowired
	private TagService tagService;

	///create tag
	@PostMapping("/")
	public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagDto tagDto)
	{
		TagDto createdTag = tagService.createTag(tagDto);
		
		return new ResponseEntity<TagDto>(createdTag, HttpStatus.CREATED);
	}
	
	///Get all tags
	@GetMapping("/")
	public ResponseEntity<List<TagDto>> getAllTags()
	{
		List<TagDto> allTags = tagService.getAllTags();
		
		return new ResponseEntity<List<TagDto>>(allTags, HttpStatus.OK);
	}
	
	///Delete Tags
	@DeleteMapping("/{tagId}")
	public ApiResponse deleteTag(@PathVariable("tagId") int tagId)
	{
		///delete tags
		tagService.deleteTag(tagId);
		
		return new ApiResponse("Tag Successfully Deleted !!!", true);
	}
}
