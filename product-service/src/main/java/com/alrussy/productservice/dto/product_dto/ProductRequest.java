package com.alrussy.productservice.dto.product_dto;
import java.util.List;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.DepartmentId;
import com.alrussy.productservice.entity.id.ProductId;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;


public record ProductRequest(String name,Double price,Double discount,String currency,Boolean isFeature,@NotNull Long categoryId,Long departmentId,Long brandId,List<String> imageUrls) {

	public Product mapToprProduct() {
	Department department=Department.builder().id(DepartmentId.builder().departmentId(departmentId).categoryId(categoryId).build()).build() ;
	BrandCategory brandCategory=BrandCategory.builder()
			.brandCategoryId(
					BrandCategoryId.builder()
					.brandId(brandId)
					.categoryId(categoryId)
					.build()
					)
			.brand(Brand.builder().id(brandId).build())
			
			.category(Category.builder().id(categoryId).build())
			.build();
	ProductId id=ProductId.builder().categoryId(categoryId).build();
		return Product.builder()
				.id(id)
				.name(name)
				.price(price)
				.discount(discount)
				.currency(currency)
				.isFeature(isFeature)
				.brandId(brandId)
				.department(department)
				.brandCategory(brandCategory)
				.imageUrls(imageUrls)
				.build();
	}

}
