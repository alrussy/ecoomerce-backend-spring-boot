package com.alrussy.productservice.dto.brand_dto;

import java.util.List;

import com.alrussy.productservice.dto.category_dto.CategoryResponse;



public record BrandResponse( Long id, String name, String imageUrl, List<CategoryResponse> categories){

	
}
