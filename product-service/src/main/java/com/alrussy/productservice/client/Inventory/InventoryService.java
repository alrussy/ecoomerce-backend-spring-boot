package com.alrussy.productservice.client.Inventory;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;
import com.alrussy.productservice.service.DetailsValueService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final DetailsValueService detailsValueService;
	private final InventoryClient inventoryClient;
	
	
	public List<Inventory> getInventory(Long productId) {
		
		
		List<InventoryResponse> inventoryResponses=inventoryClient.getInventoriesBySkuCode(productId.toString());
				
				//client.get().uri("/api/inventory/item/"+productId).retrieve().body(InventoryResponse[].class).clone();

		return inventoryResponses.stream().map(t ->Inventory.builder().id(t.getId()).productId(productId).quentity(t.getQuantity()).values(mapToDetailsValues(t.getValues())).build() ).toList();
	}
	
	private List<DetailsValueResponse> mapToDetailsValues(List<Long> ids){
				
		return ids.stream().map(t ->detailsValueService.findById(t)).toList();
		
		
	}
}
