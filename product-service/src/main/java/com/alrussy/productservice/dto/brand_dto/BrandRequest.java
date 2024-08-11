package com.alrussy.productservice.dto.brand_dto;

import java.util.List;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class BrandRequest {

	private String name;
	private String imageUrl;
	@NotEmpty
	@NotNull
	private List<Long> categoryIds;

	public Brand mapToBrand() {
		return Brand.builder().name(name).imageUrl(imageUrl)
				.brandCategory(categoryIds != null
						? categoryIds.stream()
								.map(t -> BrandCategory.builder().brandCategoryId(BrandCategoryId.builder().categoryId(t).build())
												.category(Category.builder().id(t).build()).build())
										
								.toList()
						: null)
				.build();

	}
}
