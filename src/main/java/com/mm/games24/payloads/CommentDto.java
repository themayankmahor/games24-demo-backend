package com.mm.games24.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

	private int id;
	
	@NotBlank
	@Size(min = 1, message = "Content cannot be blank")
	private String content;
	
}
