package com.alrussy.productservice.client.Inventory;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface InventoryClient {

	 @GetExchange("/api/inventory/item/{skuCode}")
	    public List<InventoryResponse> getInventoriesBySkuCode(@PathVariable("skuCode") String skuCode);
}
