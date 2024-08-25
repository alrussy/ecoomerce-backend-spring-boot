package com.alrussy.order_service.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.order_service.dto.OrderRequest;
import com.alrussy.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    

    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        service.placeOrder(orderRequest);
        return "order Placed Successfully";
    }
    @GetMapping
    public String placeOrderTest(){
        return service.placeOrderTest();
    }
//    @PutMapping
//    public String inventoryOrder(@RequestBody OrderRequest order) {
//		return service.InventoryOrdser(order);
//    	
//    }
    		
}
