package com.alrussy.productservice.dto.product_dto;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.table.BrandCategory;



public record ProductRequest(String name,Double price,Boolean isActivity ,Long categoryId,Long brandId) {

	
	
	public Product mapToprProduct() {
		
	
		return Product.builder()
				.name(name)
				.price(price)
				.isActivity(isActivity)
				.brandCategory(BrandCategory.builder().category(Category.builder().id(brandId).build()).build())
				.build();
	}

}
