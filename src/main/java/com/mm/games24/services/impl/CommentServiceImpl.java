package com.mm.games24.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.games24.entities.Comment;
import com.mm.games24.payloads.CommentDto;
import com.mm.games24.repository.CommentRepo;
import com.mm.games24.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	///Create comment
	@Override
	public CommentDto createComment(CommentDto commentDto) {
		
		//convert dto to obj
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		//save to database
		this.commentRepo.save(comment);
		
		return this.modelMapper.map(comment, CommentDto.class);
	}

}
