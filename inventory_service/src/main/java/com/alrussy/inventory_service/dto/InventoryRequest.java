package com.alrussy.inventory_service.dto;

import java.util.List;

import com.alrussy.inventory_service.model.Details;
import com.alrussy.inventory_service.model.Inventory;

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
public class InventoryRequest {
	private String productId;
	private Integer quantity;
	private List<DetailsRequest> details;
	
	
	
	
	public Inventory mapToInventory(){
		
		return Inventory.builder()
		.productId(productId)
		.quantity(quantity)
		.details(details.stream().map(d->Details.builder().valueId(d.getValueId()).build()).toList())
		.build();
		
	}
	

}