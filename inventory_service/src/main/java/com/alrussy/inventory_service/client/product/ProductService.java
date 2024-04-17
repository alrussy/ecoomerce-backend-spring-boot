package com.alrussy.inventory_service.client.product;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final DetailsClient client;
	
	
	public DetailsProduct getDetails(Long id) {
		DetailsProduct detailsProduct=client.getDetailsProduct(id);
		return detailsProduct;
		
	}

}
