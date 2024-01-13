package com.mm.games24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.games24.payloads.CommentDto;
import com.mm.games24.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	///create comment
	@PostMapping("/")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto)
	{
		CommentDto createdComment = this.commentService.createComment(commentDto);
		
		return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
	}
}
