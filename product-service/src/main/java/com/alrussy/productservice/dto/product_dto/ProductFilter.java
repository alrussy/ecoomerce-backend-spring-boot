package com.alrussy.productservice.dto.product_dto;

public record ProductFilter(String name,PriceFilter price,Long categoryId,Long brandId) {

}

