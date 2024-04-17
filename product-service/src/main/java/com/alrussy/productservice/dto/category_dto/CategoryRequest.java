package com.alrussy.productservice.dto.category_dto;

import com.alrussy.productservice.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
	
	private String name;
	private String imageUrl;

	
	public Category mapToCategory() {
		return Category.builder()
				.name(name)
				.imageUrl(imageUrl)
				.build();
		}
}
