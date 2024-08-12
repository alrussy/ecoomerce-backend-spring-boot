package com.alrussy.productservice.dto.sku_product_dto;

import java.util.List;

import com.alrussy.productservice.dto.details_dto.DetailsResponse;
import com.alrussy.productservice.dto.product_dto.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuProductResponse {
	
	private String skuCode;
	private ProductResponse product;
	private List<DetailsResponse> details;

	
}
