package com.alrussy.productservice.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.alrussy.productservice.dto.sku_product_dto.SkuProductRequest;

@HttpExchange
public interface InventoryClient {

	 @GetExchange("/api/inventory/item/{skuCode}")
	    public List<SkuProductRequest> getInventoriesBySkuCode(@PathVariable("skuCode") String skuCode);
}
