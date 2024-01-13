package com.mm.games24.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotBlank
	@Size(min = 1, message = "Category title cannot be blank")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 1, message = "Category description cannot be blank")
	private String categoryDescription;
	
}
