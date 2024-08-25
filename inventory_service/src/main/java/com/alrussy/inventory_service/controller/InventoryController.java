package com.alrussy.inventory_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.inventory_service.dto.InventoryOrderRequest;
import com.alrussy.inventory_service.dto.InventoryResponse;
import com.alrussy.inventory_service.dto.LineProduct;
import com.alrussy.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
 
    private final InventoryService service;

    
    
    @GetMapping("/sku/{skuCode}")
    public ResponseEntity<InventoryResponse> getInventoriesBySkuCode(@PathVariable("skuCode") String id){
       return ResponseEntity.ok(service.getInventoriesBySkuCode(id));

    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> findAll(){
       return ResponseEntity.ok(service.findAll());

    }

    @PostMapping("/store")
    public String store(@RequestBody List<LineProduct> lineProducts) {  
        log.info("====================send inventory request is successfuly");
        return service.store(lineProducts);
    }
  
    @GetMapping("/store")
    public String store() {  
        log.info("====================send inventory request is successfuly");
        return service.storeTest();
    }
    @GetMapping("/order")
    public String order() {  
        log.info("====================send inventory request is successfuly");
        return service.order();
    }
     
    @PutMapping("/order")
    public String orderProduct(@RequestBody InventoryOrderRequest orderRequest) { 
        log.info("order id={}",orderRequest.getOrderId());
        orderRequest.getLineProducts().stream().forEach(t->
        log.info(" skuCode={} , quentity={}",t.getSkuCode(),t.getQuentity()));
        
    	
        return service.order(orderRequest);
    }

}
