package com.alrussy.productservice.dto.category_dto;

import java.util.List;

import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.id.CategoryDetailsNameId;
import com.alrussy.productservice.entity.table.CategoryDetailsName;

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
	private List<Long> detailsNameIds;

	
	public Category mapToCategory() {
		return Category.builder()
				.name(name)
				.imageUrl(imageUrl)
				.categoryDetailsNames(detailsNameIds.stream().map(id->CategoryDetailsName.builder().id(CategoryDetailsNameId.builder().detailsnameId(id).build()).build()).toList())
				.build();
		}
}
