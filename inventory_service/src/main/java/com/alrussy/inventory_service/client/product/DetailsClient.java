package com.alrussy.inventory_service.client.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="product-service",path="/detailsValues")
public interface DetailsClient {
	
	@GetMapping("/{id}")
	public DetailsProduct getDetailsProduct(@PathVariable Long id);

}
