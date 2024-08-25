package com.alrussy.inventory_service.dto;

import java.util.List;

import com.alrussy.inventory_service.client.product.DetailsProduct;
import com.alrussy.inventory_service.model.ActionInventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
	private String skuCode;
	private Integer quantity;	
	private List<ActionInventory> actionInventories;
}