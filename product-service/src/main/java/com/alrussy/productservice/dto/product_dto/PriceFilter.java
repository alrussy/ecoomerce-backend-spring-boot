package com.alrussy.productservice.dto.product_dto;



public record PriceFilter(Double price,TypeFilter type) {
	
	public enum TypeFilter {
		
		EQUEL,GREATE_THAN,LESS_THAN,GREATE_THAN_OR_EQUEL,LESS_THAN_OR_EQUEL

	}
}
