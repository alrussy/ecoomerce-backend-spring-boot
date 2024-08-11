package com.alrussy.productservice.dto.product_dto;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.DeparmentId;
import com.alrussy.productservice.entity.table.BrandCategory;


public record ProductRequest(String name,Double price,Double discount,String currency,Boolean isFeature,Long categoryId,Long departmentId,Long brandId) {

	
	
	public Product mapToprProduct() {
		
	Category category=Category.builder().id(categoryId)
			.build();
	Brand brand=Brand.builder().id(brandId).
			build();
	Department department=Department.builder().id(DeparmentId.builder().departmentId(departmentId).categoryId(categoryId).build()).build() ;
	BrandCategoryId brandCategoryId= BrandCategoryId.builder().brandId(brandId).categoryId(categoryId).build();
		return Product.builder()
				.name(name)
				.price(price)
				.discount(discount)
				.currency(currency)
				.isFeature(isFeature)
				.department(department)
				.brandCategory(BrandCategory.builder().brandCategoryId(brandCategoryId).build())
				.build();
	}

}
