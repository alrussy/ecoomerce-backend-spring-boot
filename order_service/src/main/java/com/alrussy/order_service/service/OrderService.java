package com.alrussy.order_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.alrussy.order_service.dto.OrderLineProductsDto;
import com.alrussy.order_service.dto.OrderRequest;
import com.alrussy.order_service.model.Order;
import com.alrussy.order_service.model.OrderLineProducts;
import com.alrussy.order_service.model.enums.States;
import com.alrussy.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository ;
    private final WebClient webClient;
    //private final InventoryClient client;


    public String  placeOrder(OrderRequest orderRequest){


    
        List<OrderLineProducts> lineProducts= orderRequest.getOrderLineProducts().stream().map(lp->OrderLineProducts.builder().skuCode(lp.getSkuCode()).quentity(lp.getQuentity()).build()).toList();
        
        Order orderSave=orderRequest.mapToOrderRequest();
        orderSave.setEmailCostumer("");
        orderSave.setOrderDate(LocalDateTime.now());
        orderSave.setStatus(States.CREATED);
        
        
       Order order= repository.save(orderSave);
       
      
       
      String response= webClient.put().uri("/api/inventory/order").bodyValue( Map.of("orderId",order.getOrderNumber(),"lineProducts",lineProducts)).retrieve().bodyToMono(String.class).block();
       
       
//        List<?> result=webClient.get().uri("/api/inventory",t ->t.queryParam("skuCodes", skuCodes).build() )
//        .retrieve().bodyToMono(List.class).block();


        if(!response.isBlank()){
        	return response;

        }
        else throw new IllegalArgumentException("there have to  problem ,please again ...");
 


    }


	public String placeOrderTest() {
		
		return placeOrder(
		OrderRequest.builder().addressId(12L)
		.paymentId("pp1789")
		.total(100.0)
		.orderLineProducts
		(
				List.of(OrderLineProductsDto.builder().skuCode("123411").discount(0.0).price(20.0).quentity(5).build(),
				OrderLineProductsDto.builder().skuCode("123413").discount(0.0).price(24.0).quentity(5).build()))
		.build()
		);
		
	}
    
   
}
