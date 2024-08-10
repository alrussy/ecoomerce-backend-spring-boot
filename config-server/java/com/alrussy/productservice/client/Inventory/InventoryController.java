package com.alrussy.productservice.client.Inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService inventoryService;

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getInventories(@PathVariable Long id){
		return ResponseEntity.ok(inventoryService.getInventory(id));
	}
}
