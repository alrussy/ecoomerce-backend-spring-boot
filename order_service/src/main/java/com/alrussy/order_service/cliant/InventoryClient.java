package com.alrussy.order_service.cliant;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alrussy.order_service.model.OrderLineProducts;


@FeignClient(name = "inventory-service",path = "/api/inventory")
public interface InventoryClient {


    @PutMapping("/order")
     public String order(@RequestBody OrderLineProducts order);


}
