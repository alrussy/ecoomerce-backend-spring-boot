package com.alrussy.productservice.dto.product_dto;

import com.alrussy.productservice.dto.brand_dto.BrandResponse;
import com.alrussy.productservice.dto.category_dto.CategoryResponse;
import com.alrussy.productservice.dto.department_dto.DepartmentResponse;

public record ProductResponse(
		Long id ,String name,Double price,Double discount,Double priceAfterDiscount,Boolean isActivity,CategoryResponse category,DepartmentResponse department,BrandResponse brand) {

	
	
}
