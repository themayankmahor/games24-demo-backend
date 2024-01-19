package com.mm.games24.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TagDto {

	private int tagId;
	
	@NotBlank
	@Size(min = 1, message = "Tag title cannot be blank")
	private String tagTitle;
	
}
