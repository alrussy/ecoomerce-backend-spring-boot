package com.alrussy.productservice.dto.inventory_dto;

import java.util.List;

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
public class InventoryRequest {
	private Integer quantity;
	private List<Details> values;
	
	
}