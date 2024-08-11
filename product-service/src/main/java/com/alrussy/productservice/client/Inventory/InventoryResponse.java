package com.alrussy.productservice.client.Inventory;

import java.util.List;

import com.alrussy.productservice.dto.details_dto.DetailsResponse;
import com.alrussy.productservice.entity.Details;

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
	private Long id;
	private Long productId;
	private Integer quantity;
	private List<Long> values;
	
	
	
	public Inventory mapToInventory() {
		return Inventory.builder().quentity(quantity).productId(productId).values(values.stream().map(valueId->DetailsResponse.builder().id(valueId).build()).toList()).build();
	}
	
	
	
}