package com.alrussy.order_service.cliant;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PutExchange;

import com.alrussy.order_service.dto.InventoryOrder;

@FeignClient(name = "inventory-service",path = "/api/inventory")
public interface InventoryClient {


    @PutMapping("/order")
     public String orderProduct(@RequestBody InventoryOrder order);


}
