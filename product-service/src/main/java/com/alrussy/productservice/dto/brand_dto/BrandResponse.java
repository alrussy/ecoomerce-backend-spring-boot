package com.alrussy.productservice.dto.brand_dto;

import java.util.List;



public record BrandResponse( Long id, String name, String imageUrl, List<CategoryResponse> categories){

	public record CategoryResponse(Long id,String name) {		
	
	}
	
}
