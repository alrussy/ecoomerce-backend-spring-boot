package com.alrussy.productservice.dto.product_dto;

public record ProductResponse(Long id ,String name,Double price,Boolean isActivity,CategoryResponse category,BrandResponse brand) {

 
	public static record BrandResponse(Long id ,String name) {

	}
	public static record CategoryResponse(Long id ,String name) {

	}
	
}
