package com.alrussy.productservice.dto.category_dto;

import java.util.Set;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.CategoryDetailsNameId;
import com.alrussy.productservice.entity.table.BrandCategory;
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
	private Boolean isFeature;
	private String imageUrl;
	private Set<Long> brandIds;
	private Set<Long> detailsNameIds;

	public Category mapToCategory() {
		return Category.builder().name(name).isFeature(isFeature).imageUrl(imageUrl).build();
	}

	public Category mapToCategoryWithId(long categoryId) {
		return Category
				.builder().id(categoryId).name(name).isFeature(isFeature).imageUrl(
						imageUrl)
				.brandCategory(brandIds != null ? brandIds.stream()
						.map(t -> BrandCategory.builder()
								.brandCategoryId(BrandCategoryId.builder().brandId(t).categoryId(categoryId).build())
								.brand(Brand.builder().id(t).build()).build())
						.toList() : null)
				.categoryDetailsNames(detailsNameIds.stream()
						.map(id -> CategoryDetailsName.builder()
								.id(CategoryDetailsNameId.builder().detailsNameId(id).build())
								.detailsName(DetailsName.builder().id(id).build()).build())

						.toList())
				.build();
	}
}
