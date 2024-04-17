package com.alrussy.inventory_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.inventory_service.dto.InventoryOrder;
import com.alrussy.inventory_service.dto.InventoryRequest;
import com.alrussy.inventory_service.dto.InventoryResponse;
import com.alrussy.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
 
    private final InventoryService service;

    
    
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getInventoriesBySkuCode(@PathVariable("id") String id){
       return ResponseEntity.ok(service.getInventoriesByProductId(id));

    }

    @GetMapping
    public List<InventoryResponse> findAll(){
       return service.findAll();

    }

    @PostMapping
    public String storeItem(@RequestBody InventoryRequest inventory) {  
        log.info("====================send inventory request is successfuly");
        return service.storeItem(inventory);
    }
  
    
    @PutMapping("/order")
    public String orderProduct(@RequestBody InventoryOrder order) {  
        return service.orderProducts(order);
    }
    @GetMapping("/add/{id}")
    public String addQuentityToProduct(@PathVariable Long id,@RequestParam Integer quentity) {  
        return service.addQuentityToProduct(id,quentity);
    }
}
