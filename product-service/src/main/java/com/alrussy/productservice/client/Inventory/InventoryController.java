package com.alrussy.productservice.client.Inventory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
	

	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getInventories(@PathVariable Long id){
//		return ResponseEntity.ok(inventoryService.getInventory(id));
//	}
}
