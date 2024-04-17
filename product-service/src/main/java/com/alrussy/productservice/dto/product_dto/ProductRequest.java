package com.alrussy.productservice.dto.product_dto;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.table.BrandCategory;



public record ProductRequest(String name,Double price,Boolean isActivity ,Long categoryId,Long brandId) {

	
	
	public Product mapToprProduct() {
		
	Category category=Category.builder().id(categoryId)
			.build();
	Brand brand=Brand.builder().id(brandId).
			build();
	
	BrandCategoryId brandCategoryId= BrandCategoryId.builder().brand(brand).category(category).build();
		return Product.builder()
				.name(name)
				.price(price)
				.isActivity(isActivity)
				.brandCategory(BrandCategory.builder().brandCategoryId(brandCategoryId).build())
				.build();
	}

}
