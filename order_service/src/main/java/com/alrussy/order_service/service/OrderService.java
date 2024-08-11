package com.alrussy.order_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.alrussy.order_service.cliant.InventoryClient;
import com.alrussy.order_service.dto.InventoryOrder;
import com.alrussy.order_service.dto.InventoryOrderRequest;
import com.alrussy.order_service.dto.OrderRequest;
import com.alrussy.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository ;
    
    private final WebClient webClient;
    private final InventoryClient client;


//    public void  placeOrder(OrderRequest orderRequest){
//
//
//        List<String> skuCodes= orderRequest.getItems().stream().map(item->item.getSkuCode()).toList();
//
//        List<?> result=webClient.get().uri("/api/inventory",t ->t.queryParam("skuCodes", skuCodes).build() )
//        .retrieve().bodyToMono(List.class).block();
//
//
//        if(result.isEmpty()){
//            repository.save(orderRequest.mapToOrderRequest());
//
//        }
//        else throw new IllegalArgumentException("the items is "+result+" not found");
// 
//
//
//    }
//    
    public String InventoryOrdser(OrderRequest order) {
    	
    	String orderNumber=UUID.randomUUID().toString();
    	
    	
    	String result= client.orderProduct(
    			
    			InventoryOrder.builder()
    			.orderNumber(orderNumber)
    			.inventoryOrders(
    					order.getCard().stream().map(
    							i->InventoryOrderRequest.builder()
    							.InventoryId(i.getInventoryId())
    							.quentity(i.getQuentity()).build()
    							
    							).toList())
    			.build()
    			
    			);
    	order.setOrdeNumber(orderNumber);
    	repository.save(order.mapToOrderRequest());
    	
    	return result;
    	
    	
    }
}
