package com.mm.games24.services;

import java.util.List;

import com.mm.games24.payloads.TagDto;

public interface TagService {

	///Create tag
	TagDto createTag(TagDto tagDto);
	
	///Get all
	List<TagDto> getAllTags();
	
	///Delete tags
	void deleteTag(int tagId);
}
