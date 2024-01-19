package com.mm.games24.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.games24.entities.Tag;
import com.mm.games24.exceptions.ResourceNotFoundException;
import com.mm.games24.payloads.TagDto;
import com.mm.games24.repository.TagRepo;
import com.mm.games24.services.TagService;

@Service
public class TagServiceImpl implements TagService{
	
	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	///Create Tag
	@Override
	public TagDto createTag(TagDto tagDto) {
		
		Tag tag = this.modelMapper.map(tagDto, Tag.class);
		
		Tag savedTag = tagRepo.save(tag);
		
		return this.modelMapper.map(savedTag, TagDto.class);		
	}
	
	///Get all tags
	@Override
	public List<TagDto> getAllTags() {
		
		List<Tag> tags = tagRepo.findAll();
		
		return tags.stream().map((tag) -> this.modelMapper.map(tag, TagDto.class)).collect(Collectors.toList());
	}
	
	///Delete Tags
	@Override
	public void deleteTag(int tagId) {
		
		///get tag
		Tag tag = tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "tag ID", tagId));
		
		///Delete from database
		tagRepo.delete(tag);
	}

}
