package com.mm.games24.services;

import com.mm.games24.payloads.CommentDto;

public interface CommentService {

	///Create Comment
	CommentDto createComment(CommentDto commentDto);
	
}
