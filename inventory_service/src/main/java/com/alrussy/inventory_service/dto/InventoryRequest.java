package com.alrussy.inventory_service.dto;

import java.util.List;

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
	private List<LineProduct> lineProducts;
	
	public List<Inventory> mapToListInventory(){
		
		return lineProducts.stream().map(lp->Inventory.builder()
				.skuCode(lp.getSkuCode())
				.quantity(lp.getQuentity())
				.build()).toList();
		
		
	}
	

}