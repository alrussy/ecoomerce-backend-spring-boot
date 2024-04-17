package com.alrussy.order_service.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.order_service.dto.InventoryOrder;
import com.alrussy.order_service.dto.OrderRequest;
import com.alrussy.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    

//    @PostMapping
//    public String placeOrder(@RequestBody OrderRequest orderRequest){
//        service.placeOrder(orderRequest);
//        return "order Placed Successfully";
//    }
    
    @PutMapping
    public String inventoryOrder(@RequestBody OrderRequest order) {
		return service.InventoryOrdser(order);
    	
    }
    		
}
